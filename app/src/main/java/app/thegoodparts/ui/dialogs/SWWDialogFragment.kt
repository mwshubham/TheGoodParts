package app.thegoodparts.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import app.thegoodparts.utilities.view.swwDialog

/**
 * Something Went Wrong Dialog Fragment
 */
class SWWDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return swwDialog()
    }
}