package ua.nedash.movies.extensions

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import ua.nedash.movies.R

inline fun <reified T> Context.getListFromJsonResource(res: Int): T {
    return Gson().fromJson(
        resources.openRawResource(res).bufferedReader().use { it.readText() },
        T::class.java
    )
}

fun AppCompatImageView.setImageUrlWithGlide(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)