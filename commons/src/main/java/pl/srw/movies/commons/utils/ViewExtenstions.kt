package pl.srw.movies.commons.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "placeholder", requireAll = false)
fun ImageView.setImageUrl(url: String?, placeholder: Drawable?) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}