package app.thegoodparts.adapters

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formattedPublishedAt")
fun formattedPublishedAt(textView: TextView, publishedAt: String?) {
    try {
        publishedAt ?: return
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(publishedAt)
        date ?: return

        // Sun, 22 May 2020
        val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
        outputFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        textView.text = outputFormat.format(date)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

@BindingAdapter("formattedTimeAgo")
fun formattedTimeAgo(textView: TextView, publishedAt: String?) {
    try {
        publishedAt ?: return
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("GMT")
        val time = inputFormat.parse(publishedAt)?.time ?: return
        val now = System.currentTimeMillis()
        textView.text = DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.MINUTE_IN_MILLIS
        )
    } catch (e: Exception) {
        Timber.e(e)
    }
}
