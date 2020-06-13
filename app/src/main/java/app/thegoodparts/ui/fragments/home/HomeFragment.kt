package app.thegoodparts.ui.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.thegoodparts.ApiState
import app.thegoodparts.R
import app.thegoodparts.constants.ErrorType
import app.thegoodparts.databinding.FragmentHomeBinding
import app.thegoodparts.extensions.observeNotNull
import app.thegoodparts.ui.fragments.BaseFragment
import app.thegoodparts.ui.fragments.newsdetails.NewsDetailsFragmentDirections
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsArticlesAdapter

    private val viewModel by viewModels<HomeFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = NewsArticlesAdapter {
            if (it is NewsAdapterEvent.ClickEvent) {
                findNavController().navigate(
                    NewsDetailsFragmentDirections.startFragmentNewsDetails(
                        it.newsArticle.publishedAt
                    ),
                    it.extras
                )
            }
        }
        binding.rvNews.adapter = adapter

        showNewsApiAttributionLinkIfRequired()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            findNavController().navigate(R.id.dialog_fragment_wip)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.newsArticles.observeNotNull(this) { state ->
            Timber.i("state: $state")
            when (state) {
                is ApiState.Success -> adapter.submitList(state.data)
                is ApiState.Loading -> {
                }
                is ApiState.Error -> {
                    if (binding.rvNews.adapter?.itemCount == 0) {
                        when (state.errorType) {
                            ErrorType.NetworkError.InternetUnavailable -> findNavController().navigate(
                                R.id.dialog_fragment_internet_unavailable
                            )
                            else -> findNavController().navigate(R.id.dialog_fragment_sww)
                        }
                    }
                }
            }
        }
    }

    private fun showNewsApiAttributionLinkIfRequired() {
        if (showNewsApiAttributionLink) {
            Snackbar.make(
                binding.root,
                "Powered by NewsAPI.org",
                Snackbar.LENGTH_LONG
            )
                .setAction("Visit") {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org/"))
                    startActivity(browserIntent)
                }
                .show()
            showNewsApiAttributionLink = false
        }
    }

    companion object {
        var showNewsApiAttributionLink = true
    }
}
