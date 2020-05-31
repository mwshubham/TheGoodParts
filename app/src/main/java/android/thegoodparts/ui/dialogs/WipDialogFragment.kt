package android.thegoodparts.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.thegoodparts.utilities.view.createWipDialog
import androidx.fragment.app.DialogFragment

/**
 * Work In Progress Dialog Fragment
 */
class WipDialogFragment : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return createWipDialog()
    }
}