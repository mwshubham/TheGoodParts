package android.thegoodparts.ui.activities

import android.os.Bundle
import android.thegoodparts.R
import android.thegoodparts.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}