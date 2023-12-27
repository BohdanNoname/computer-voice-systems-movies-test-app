package ua.nedash.movies.extensions

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

fun Fragment.openSite(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}

@SuppressLint("SimpleDateFormat")
fun String.fromDateStringToDateLong(): Long {
    val a = "03 September 2020"
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
    val inputDate: Date

    try {
        inputDate = simpleDateFormat.parse(a)!!
        println(inputDate.time)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}