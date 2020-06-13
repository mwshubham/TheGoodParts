package app.thegoodparts.ui.fragments.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import app.thegoodparts.R
import app.thegoodparts.databinding.FragmentNewsDetailsBinding
import app.thegoodparts.extensions.observeNotNull
import app.thegoodparts.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    private val viewModel: NewsDetailsFragmentVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news_details, container, false
        )
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.publishedAt = args.publishedAt
        binding.root.transitionName = args.publishedAt

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.newsArticle.observeNotNull(viewLifecycleOwner) { newsArticle ->
            Timber.i("newsArticle: $newsArticle")
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {

    }

    private fun initObservers() {
    }

}