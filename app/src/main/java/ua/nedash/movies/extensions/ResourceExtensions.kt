package ua.nedash.movies.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson

inline fun <reified T> Context.getListFromJsonResource(res: Int): T {
    return Gson().fromJson(
        resources.openRawResource(res).bufferedReader().use { it.readText() },
        T::class.java
    )
}

fun AppCompatImageView.setImageUrlWithGlide(url: String) =
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)