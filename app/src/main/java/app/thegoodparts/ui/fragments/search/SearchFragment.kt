package app.thegoodparts.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import app.thegoodparts.ApiState
import app.thegoodparts.R
import app.thegoodparts.data.source.remote.response.Address
import app.thegoodparts.data.source.remote.response.SearchResponse
import app.thegoodparts.databinding.FragmentSearchBinding
import app.thegoodparts.extensions.afterTextChangedDebounce
import app.thegoodparts.extensions.observe
import app.thegoodparts.testing.OpenForTesting
import app.thegoodparts.ui.ScreenState
import app.thegoodparts.ui.fragments.BaseFragment
import app.thegoodparts.utilities.CoreKeyboardUtils
import app.thegoodparts.utilities.CoreNetworkUtils
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
open class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SearchFragmentVM
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        CoreKeyboardUtils.showKeyboard(requireActivity())

        val adapter = SearchAdapter()
        binding.rvSearchResults.adapter = adapter

        binding.tvSearch.afterTextChangedDebounce { queryString ->
            Timber.i("doAfterTextChanged: $queryString")
            if (queryString.isBlank()) {
                viewModel.clearSearchInput()
                @Suppress("UNCHECKED_CAST")
                (binding.rvSearchResults.adapter as ListAdapter<Address, SearchAdapter.ViewHolder>).submitList(
                    emptyList()
                )
                return@afterTextChangedDebounce
            }
            if (!CoreNetworkUtils.isInternetAvailable(requireContext())) {
                findNavController().navigate(R.id.dialog_fragment_internet_unavailable)
                return@afterTextChangedDebounce
            }
            viewModel.userTyping()
            viewModel.getAddressList(queryString.trim())
        }
        initObservers()
    }

    private fun initObservers() {
        observe(viewModel.screenState, ::onScreenStateChange)
        observe(viewModel.searchViewState, ::onSearchViewStateChange)
        observe(viewModel.apiState, ::onApiStateChange)
    }

    private fun onScreenStateChange(screenState: ScreenState) {
        Timber.i(screenState.toString())
        when (screenState) {
            is ScreenState.Dismiss -> {
                CoreKeyboardUtils.hideKeyboard(requireActivity())
                findNavController().popBackStack()
            }
        }
    }

    private fun onSearchViewStateChange(searchViewState: SearchViewState) {
        Timber.i(searchViewState.toString())
        when (searchViewState) {
            is SearchViewState.Typing -> {
                // Do Nothing
            }
            // Cleared State will be called twice when cleared from clear button
            is SearchViewState.Cleared -> {
                if (!binding.tvSearch.text?.toString().isNullOrBlank()) {
                    binding.tvSearch.setText("")
                }
            }
        }
    }

    private fun onApiStateChange(apiState: ApiState<SearchResponse>) {
        Timber.i(apiState.toString())
        when (apiState) {
            is ApiState.Loading -> {
                // Do Nothing
            }

            is ApiState.Success -> {
                Timber.i("response: ${apiState.data}")
                @Suppress("UNCHECKED_CAST")
                (binding.rvSearchResults.adapter as ListAdapter<Address, SearchAdapter.ViewHolder>)
                    .submitList(
                        apiState.data.data.addressList
                    )
            }

            is ApiState.Error -> {
                findNavController().navigate(R.id.dialog_fragment_sww)
            }
        }
    }

}
