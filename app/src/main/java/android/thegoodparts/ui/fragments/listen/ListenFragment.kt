package android.thegoodparts.ui.fragments.listen

import android.os.Bundle
import android.thegoodparts.R
import android.thegoodparts.ui.fragments.BaseFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListenFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listen, container, false)
    }

}