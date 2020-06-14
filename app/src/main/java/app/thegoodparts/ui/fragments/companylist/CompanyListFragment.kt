package app.thegoodparts.ui.fragments.companylist

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import app.thegoodparts.ApiState
import app.thegoodparts.R
import app.thegoodparts.constants.CoreLoggingConstants
import app.thegoodparts.data.source.remote.response.CompanyMemberRelation
import app.thegoodparts.databinding.CompanyListFragmentBinding
import app.thegoodparts.extensions.observeNotNull
import app.thegoodparts.ui.fragments.BaseFragment
import app.thegoodparts.ui.fragments.sortby.SortFor
import app.thegoodparts.ui.fragments.sortby.SortType
import app.thegoodparts.ui.fragments.sortby.SortTypeEventViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class CompanyListFragment : BaseFragment() {

    private lateinit var binding: CompanyListFragmentBinding

    @ExperimentalCoroutinesApi
    private val viewModel: CompanyListViewModel by viewModels()

    private lateinit var sortTypeEventViewModel: SortTypeEventViewModel
    private var companyMembersList: List<CompanyMemberRelation> = emptyList()
    private var sortType: SortType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.company_list_fragment, container, false
            )
            binding.viewModel = viewModel
            binding.rvCompanies.apply {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                adapter = CompanyListAdapter {
                    if (it is CompanyListAdapterEvent.ClickEvent) {
                        CompanyListFragmentDirections.actionCompanyListFragmentToCompanyDetailFragment(
                            it.company._id
                        ).let { navDirections ->
                            findNavController().navigate(navDirections)
                        }
                    }
                    if (it is CompanyListAdapterEvent.FollowEvent) {
                        viewModel.toggleFollowingState(it.company._id)
                    }
                }
            }
            sortTypeEventViewModel =
                ViewModelProvider(requireActivity()).get(SortTypeEventViewModel::class.java)
        }
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        initObservers()
    }

    private fun initObservers() {
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
        sortTypeEventViewModel.event.observeNotNull(viewLifecycleOwner) { sortTypeEvent ->
            Timber.i("sortTypeEvent: $sortTypeEvent")
            if (sortTypeEvent.sortFor == SortFor.COMPANY) {
                sortType = sortTypeEvent.sortType
                submitList()
//              binding.rvCompanies.smoothScrollToPosition(0)
            }
        }
        viewModel.companies.observeNotNull(viewLifecycleOwner) { state ->
            Timber.i("state: $state")
            when (state) {
                is ApiState.Loading -> {
                }
                is ApiState.Success -> {
                    companyMembersList = state.data
                    submitList()
                }
                is ApiState.Error -> {
                }
            }
        }
    }

    private fun submitList() {
        if (sortType == SortType.NAME_ASC) {
            companyMembersList = companyMembersList.sortedBy { it.company.company }
        } else if (sortType == SortType.NAME_DESC) {
            companyMembersList = companyMembersList.sortedByDescending { it.company.company }
        }
        (binding.rvCompanies.adapter as CompanyListAdapter).submitList(
            companyMembersList.map { companyMemberRelation ->
                companyMemberRelation.company.apply {
                    members = companyMemberRelation.members
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_company_list_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_sort_by -> {
                findNavController().navigate(
                    CompanyListFragmentDirections
                        .actionToSortBottomSheetDialogFragment(
                            SortFor.COMPANY
                        )
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}