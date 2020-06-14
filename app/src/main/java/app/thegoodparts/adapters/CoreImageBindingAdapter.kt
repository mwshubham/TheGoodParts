package app.thegoodparts.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.thegoodparts.GlideApp
import app.thegoodparts.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(view)
    }
}