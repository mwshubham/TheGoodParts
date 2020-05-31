package android.thegoodparts.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.thegoodparts.utilities.view.swwDialog
import androidx.fragment.app.DialogFragment

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