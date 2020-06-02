package app.thegoodparts.ui

/**
 * Different states for [ScreenState].
 *
 * @see BaseViewState
 */
sealed class ScreenState : BaseViewState {

    object Dismiss : ScreenState()

    override fun toString(): String {
        if (isDismiss()) return "Dismiss"
        return ""
    }

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Dismiss].
     *
     * @return True if view is in dismiss state, otherwise false.
     */
    fun isDismiss() = this is Dismiss

}