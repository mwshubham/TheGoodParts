package app.thegoodparts.ui.fragments.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import app.thegoodparts.R
import app.thegoodparts.databinding.FragmentMoreBinding
import app.thegoodparts.ui.fragments.BaseFragment
import javax.inject.Inject

class MoreFragment : BaseFragment() {

    private lateinit var binding: FragmentMoreBinding

    @Inject
    lateinit var viewModel: MoreFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_more, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initClickListeners()
        initObservers()
    }

    private fun initClickListeners() {
        val showWipDialogFragmentClickListener = View.OnClickListener {
            showWipDialogFragment()
        }
        binding.tvBookmark.setOnClickListener(showWipDialogFragmentClickListener)
        binding.tvFilterContent.setOnClickListener(showWipDialogFragmentClickListener)
        binding.tvWhatNew.setOnClickListener(showWipDialogFragmentClickListener)
        binding.tvWhatUpcoming.setOnClickListener { showWipDialogFragment() }
        binding.tvSettings.setOnClickListener { showWipDialogFragment() }
        binding.tvSendFeedback.setOnClickListener { showWipDialogFragment() }
        binding.tvHelpSupport.setOnClickListener { showWipDialogFragment() }
    }

    private fun initObservers() {
    }

    private fun showWipDialogFragment() {
        findNavController().navigate(R.id.dialog_fragment_wip)
    }
}