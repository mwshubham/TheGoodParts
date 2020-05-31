package android.thegoodparts.ui.fragments.watch

import android.os.Bundle
import android.thegoodparts.R
import android.thegoodparts.ui.fragments.BaseFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WatchFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watch, container, false)
    }
}
