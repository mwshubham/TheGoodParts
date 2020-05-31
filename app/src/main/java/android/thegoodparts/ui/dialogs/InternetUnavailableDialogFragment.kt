package android.thegoodparts.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.thegoodparts.utilities.view.internetUnavailableDialog
import androidx.fragment.app.DialogFragment

/**
 * Internet Unavailable Dialog Fragment
 */
class InternetUnavailableDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return internetUnavailableDialog()
    }
}