package app.thegoodparts.ui.fragments.sortby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.thegoodparts.R
import app.thegoodparts.databinding.SortBottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: SortBottomSheetDialogFragmentBinding
    private lateinit var sortTypeEventViewModel: SortTypeEventViewModel

    private val args: SortBottomSheetDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.sort_bottom_sheet_dialog_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        initClickListener()
    }

    private fun init() {
        sortTypeEventViewModel =
            ViewModelProvider(requireActivity()).get(SortTypeEventViewModel::class.java)
        binding.args = args
    }

    private fun initClickListener() {
        binding.tvNameAscending.setOnClickListener {
            sortTypeEventViewModel.sendEvent(args.sortFor, SortType.NAME_ASC)
            findNavController().popBackStack()
        }
        binding.tvNameDescending.setOnClickListener {
            sortTypeEventViewModel.sendEvent(args.sortFor, SortType.NAME_DESC)
            findNavController().popBackStack()
        }

        binding.tvAgeAscending.setOnClickListener {
            sortTypeEventViewModel.sendEvent(args.sortFor, SortType.AGE_ASC)
            findNavController().popBackStack()
        }

        binding.tvAgeDescending.setOnClickListener {
            sortTypeEventViewModel.sendEvent(args.sortFor, SortType.AGE_DESC)
            findNavController().popBackStack()
        }
    }
}