package br.com.isgreen.study.paginggame.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Éverdes Soares on 11/18/2020.
 */

fun ImageView?.loadImage(url: String?, @DrawableRes errorPlaceholder: Int) {
    this?.let {
        Glide.with(it.context)
            .load(url)
            .apply(
                RequestOptions.circleCropTransform()
                    .centerCrop()
                    .error(errorPlaceholder)
            )
            .into(it)
    }
}