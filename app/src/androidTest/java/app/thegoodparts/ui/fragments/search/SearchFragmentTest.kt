package app.thegoodparts.ui.fragments.search

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import app.thegoodparts.R
import app.thegoodparts.data.repository.SearchRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@MediumTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {
    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

//    @Mock
//    lateinit var doubtnutRemoteDataSource: DoubtnutRemoteDataSource

    @Mock
    lateinit var searchRepository: SearchRepository

    fun setup() {
        println("setup()")
    }

    @Test
    fun launchFragmentInContainer_displayedInUi() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(searchRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)

        Espresso.onView(withId(R.id.iv_back))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_search_results))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tv_search))
            .check(ViewAssertions.matches(withHint(R.string.search)))
    }

    @Test
    fun launchFragmentInContainer_notDisplayedInUi() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(searchRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)
        Espresso.onView(withId(R.id.iv_clear_input))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.progress_bar_search))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun launchFragmentInContainer_doesNotExist() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(searchRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)
        Espresso.onView(withId(R.id.tv_bookmark)).check(ViewAssertions.doesNotExist())
    }
}