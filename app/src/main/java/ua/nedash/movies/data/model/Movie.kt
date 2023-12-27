package ua.nedash.movies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.nedash.movies.data.db.MovieEntity

data class Movies(
    @SerializedName("movies")
    val movies: List<Movie> = listOf()
)

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("rating")
    val rating: String = "",
    @SerializedName("duration")
    val duration: String = "",
    @SerializedName("genre")
    val genre: List<String> = listOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("release_date_in_millis")
    val releaseDateInMillis: Long = 0,
    @SerializedName("trailer_link")
    val trailerLink: String = "",
    @SerializedName("cover_art_url")
    val coverArtUrl: String = "",
    @SerializedName("is_on_watchlist")
    var isOnWatchlist: Boolean = false
) : Parcelable

fun Movie.toEntity(isOnWatchlist: Boolean) = MovieEntity(
    id = id,
    title = title,
    description = description,
    rating = rating,
    duration = duration,
    genre = genre.joinToString(),
    releaseDate = releaseDate,
    trailerLink = trailerLink,
    coverArtUrl = coverArtUrl,
    isOnWatchlist = isOnWatchlist
)