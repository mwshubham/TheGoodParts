package android.thegoodparts.ui.activities

import android.os.Bundle
import android.thegoodparts.R
import android.thegoodparts.databinding.ActivityMainBinding
import android.thegoodparts.utilities.CoreFirebaseUtils
import android.thegoodparts.utilities.CoreFragmentLifecycleCallbacks
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

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
        Timber.i("isInitialized: ${::viewModel.isInitialized}")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        navController = findNavController(R.id.nav_host_fragment)

        initListeners()
        CoreFirebaseUtils.logToken()
    }

    private fun initListeners() {
        binding.fabSearch.setOnClickListener {
            navigateToSearchFragment()
        }
        binding.fabShare.setOnClickListener {
            navController.navigate(R.id.dialog_fragment_wip)
        }
        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(
            CoreFragmentLifecycleCallbacks(),
            true
        )
    }

    private fun navigateToSearchFragment() {
        navController.navigate(R.id.fragment_search)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Timber.i("destination: $destination")
        if (destination.id == R.id.navigation_more || destination.id == R.id.fragment_search) {
            if (destination.id == R.id.navigation_more) {
                if (binding.fabSearch.isGone) {
                    binding.fabShare.show()
                } else {
                    binding.fabSearch.hide(object :
                        FloatingActionButton.OnVisibilityChangedListener() {
                        override fun onHidden(fab: FloatingActionButton?) {
                            super.onHidden(fab)
                            binding.fabShare.show()
                        }
                    })
                }
            } else {
                binding.fabSearch.hide()
            }
        } else if (
            destination.id in intArrayOf(
                R.id.navigation_home,
                R.id.navigation_read,
                R.id.navigation_watch,
                R.id.navigation_listen
            )
        ) {
            if (binding.fabShare.isGone) {
                binding.fabSearch.show()
            } else {
                binding.fabShare.hide(object :
                    FloatingActionButton.OnVisibilityChangedListener() {
                    override fun onHidden(fab: FloatingActionButton?) {
                        super.onHidden(fab)
                        binding.fabSearch.show()
                    }
                })
            }
        }
    }
}
