package app.thegoodparts

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import app.thegoodparts.constants.CoreLoggingConstants
import app.thegoodparts.data.source.remote.response.Member
import app.thegoodparts.databinding.CompanyDetailFragmentBinding
import app.thegoodparts.extensions.observeNotNull
import app.thegoodparts.ui.fragments.BaseFragment
import app.thegoodparts.ui.fragments.companydetails.MemberListAdapter
import app.thegoodparts.ui.fragments.companydetails.MemberListAdapterEvent
import app.thegoodparts.ui.fragments.companylist.CompanyListFragmentDirections
import app.thegoodparts.ui.fragments.sortby.SortFor
import app.thegoodparts.ui.fragments.sortby.SortType
import app.thegoodparts.ui.fragments.sortby.SortTypeEventViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import kotlin.math.abs

@AndroidEntryPoint
class CompanyDetailFragment : BaseFragment() {

    private lateinit var binding: CompanyDetailFragmentBinding
    private val args: CompanyDetailFragmentArgs by navArgs()

    private val viewModel: CompanyDetailViewModel by viewModels()

    private lateinit var sortTypeEventViewModel: SortTypeEventViewModel
    private var members: List<Member> = emptyList()
    private var sortType: SortType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.company_detail_fragment, container, false
            )
            binding.viewModel = viewModel
            binding.rvMembers.apply {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                adapter = MemberListAdapter {
                    if (it is MemberListAdapterEvent.FavEvent) {
                        viewModel.toggleMemberFavouriteState(it.member._id)
                    }
                }
            }
            binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                binding.ivSortBy.visibility =
                    if (appBarLayout.totalScrollRange - abs(verticalOffset) <= binding.tvMembersLabel.height / 2)
                        View.VISIBLE
                    else
                        View.GONE
            })
            binding.ivSortBy.setOnClickListener {
                findNavController().navigate(
                    CompanyListFragmentDirections
                        .actionToSortBottomSheetDialogFragment(
                            SortFor.MEMBER
                        )
                )
            }
            sortTypeEventViewModel =
                ViewModelProvider(requireActivity()).get(SortTypeEventViewModel::class.java)
        }
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        }

        initObservers()
    }

    private fun initObservers() {
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
        sortTypeEventViewModel.event.observeNotNull(viewLifecycleOwner) { sortTypeEvent ->
            Timber.i("sortTypeEvent: $sortTypeEvent")
            if (sortTypeEvent.sortFor == SortFor.MEMBER) {
                sortType = sortTypeEvent.sortType
                submitList()
            }
        }

        viewModel.getCompanyDetails(args.id).observeNotNull(viewLifecycleOwner) {
            binding.companyMember = it
            members = it.members
            submitList()
        }
    }

    private fun submitList() {
        when (sortType) {
            SortType.NAME_ASC -> members = members.sortedBy { it.name.getFullName() }
            SortType.NAME_DESC -> members = members.sortedByDescending { it.name.getFullName() }
            SortType.AGE_ASC -> members = members.sortedBy { it.age }
            SortType.AGE_DESC -> members = members.sortedByDescending { it.age }
        }
        (binding.rvMembers.adapter as MemberListAdapter).submitList(members)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_company_detail_fragment, menu)
        menu.findItem(R.id.item_search)
            .setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    menu.findItem(R.id.item_sort_by).isVisible = false
                    binding.tvAbout.visibility = View.GONE
                    binding.tvWebsite.visibility = View.GONE
                    binding.chipFollow.visibility = View.GONE
                    binding.llMembers.visibility = View.GONE
                    binding.fabFavorite.visibility = View.GONE
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    menu.findItem(R.id.item_sort_by).isVisible = true
                    binding.tvAbout.visibility = View.VISIBLE
                    binding.tvWebsite.visibility = View.VISIBLE
                    binding.chipFollow.visibility = View.VISIBLE
                    binding.llMembers.visibility = View.VISIBLE
                    binding.fabFavorite.visibility = View.VISIBLE
                    return true
                }
            })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_sort_by -> {
                binding.ivSortBy.performClick()
                return true
            }
            R.id.item_search -> {
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        Timber.i("newText: $newText")
                        (binding.rvMembers.adapter as MemberListAdapter).submitList(
                            members.filter {

                                it.name.first.contains(newText.toString(), ignoreCase = true)
                                        || it.name.last.contains(
                                    newText.toString(),
                                    ignoreCase = true
                                )
                                        || it.email.contains(newText.toString(), ignoreCase = true)
                                        || it.phone.contains(newText.toString(), ignoreCase = true)
                                        || it.age.contains(newText.toString(), ignoreCase = true)
                            })
                        return true
                    }

                })
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}