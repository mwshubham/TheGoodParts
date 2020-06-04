package app.thegoodparts.ui.fragments.search

class TestSearchFragment : SearchFragment() {

    override fun injectMembers() {
        this.viewModel = testViewModel
    }

    companion object {
        // static property can be set before launching the Fragment
        // to a mock instance
        lateinit var testViewModel: SearchFragmentVM
    }
}