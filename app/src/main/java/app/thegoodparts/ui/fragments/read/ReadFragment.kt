package app.thegoodparts.ui.fragments.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.thegoodparts.R
import app.thegoodparts.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read, container, false)
    }

}
