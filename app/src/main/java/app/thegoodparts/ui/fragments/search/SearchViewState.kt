package app.thegoodparts.ui.fragments.search

import app.thegoodparts.ui.BaseViewState

/**
 * Different states for [SearchViewState].
 *
 * @see BaseViewState
 */
sealed class SearchViewState : BaseViewState {

    object Typing : SearchViewState()

    object Cleared : SearchViewState()

    override fun toString(): String {
        if (isTyping()) return "Typing"
        if (isCleared()) return "Cleared"
        return ""
    }

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Typing].
     *
     * @return True if view is in typing state, otherwise false.
     */
    fun isTyping() = this is Typing

    /**
     * Check if current view state is [Cleared].
     *
     * @return True if view is in cleared state, otherwise false.
     */
    fun isCleared() = this is Cleared

}