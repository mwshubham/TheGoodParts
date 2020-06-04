package app.thegoodparts.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import app.thegoodparts.R
import app.thegoodparts.ui.activities.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

//    @Mock
//    lateinit var navController: NavController

    @Test
    fun launchSearchFragment_performClick_backPressToMainActivity() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
        activity.onActivity {
//            Navigation.setViewNavController(, navController)
//            Mockito.verify(navController).navigate(navController.popBackStack())
        }
        Espresso.onView(ViewMatchers.withId(R.id.fab_search)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_search_results)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
//        Espresso.onView(ViewMatchers.withId(R.id.iv_back)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_view)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}