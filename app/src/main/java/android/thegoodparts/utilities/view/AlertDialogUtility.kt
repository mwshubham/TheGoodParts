package android.thegoodparts.utilities.view

import android.content.Context
import android.thegoodparts.R
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@Suppress("unused")
fun Fragment.showWipDialog() =
    AlertDialogUtility.wipDialog(requireContext(), true)

fun DialogFragment.createWipDialog() =
    AlertDialogUtility.wipDialog(requireContext(), false)

fun DialogFragment.swwDialog() =
    AlertDialogUtility.swwDialog(requireContext(), false)

fun DialogFragment.internetUnavailableDialog() =
    AlertDialogUtility.internetUnavailableDialog(requireContext(), false)

object AlertDialogUtility {

    fun wipDialog(context: Context, show: Boolean) = MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.work_in_progress))
        .setMessage(context.getString(R.string.feature_available_soon_thanks_for_showing_interest))
        .setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        .create().apply {
            if (show) {
                show()
            }
        }

    fun swwDialog(context: Context, show: Boolean) = MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.something_went_wrong))
        .setMessage(context.getString(R.string.please_stay_calm_our_team_is_working_on_it))
        .setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        .create().apply {
            if (show) {
                show()
            }
        }

    fun internetUnavailableDialog(context: Context, show: Boolean) =
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.internet_unavailable))
            .setMessage(context.getString(R.string.please_check_your_internet_connection_and_retry))
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .create().apply {
                if (show) {
                    show()
                }
            }
}
