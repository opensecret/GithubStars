package com.opensecret.githubstars.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imgResUrl")
    fun setImageResource(v: ImageView, resUrl: String) {
        Glide.with(v.context)
            .load(resUrl)
            .apply(RequestOptions().centerCrop())
            .into(v)
    }
}