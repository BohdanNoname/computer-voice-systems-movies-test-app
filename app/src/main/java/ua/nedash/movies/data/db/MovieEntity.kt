package ua.nedash.movies.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ua.nedash.movies.data.model.Movie
import ua.nedash.movies.data.model.Movies

@Entity(tableName = "movies")
@Parcelize
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("title")
    val title: String = "",
    @ColumnInfo("description")
    val description: String = "",
    @ColumnInfo("rating")
    val rating: String = "",
    @ColumnInfo("duration")
    val duration: String = "",
    @ColumnInfo("genre")
    val genre: String = "",
    @ColumnInfo("release_date")
    val releaseDate: String = "",
    @ColumnInfo("trailer_link")
    val trailerLink: String = "",
    @ColumnInfo("cover_art_url")
    val coverArtUrl: String = "",
    @ColumnInfo("is_on_watchlist")
    val isOnWatchlist: Boolean = false
) : Parcelable


fun MovieEntity.toMovie(): Movie {
    val genreList = genre.split(",")

    return Movie(
        id = id,
        title = title,
        description = description,
        rating = rating,
        duration = duration,
        genre = genreList,
        releaseDate = releaseDate,
        trailerLink = trailerLink,
        coverArtUrl = coverArtUrl,
        isOnWatchlist = isOnWatchlist
    )
}

fun List<MovieEntity>.toMovies() = Movies(map { it.toMovie() })