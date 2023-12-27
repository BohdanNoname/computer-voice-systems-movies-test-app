package ua.nedash.movies.use_case

import ua.nedash.movies.data.db.MovieRepository
import ua.nedash.movies.data.model.Movie
import ua.nedash.movies.data.model.toEntity

class ToWatchlist(private val movieDao: MovieRepository) {

    suspend operator fun invoke(movie: Movie): Boolean {
        val oldMovie = movieDao.getById(movie.id)

        return if (oldMovie == null) {
            movieDao.insert(movie.toEntity(isOnWatchlist = true))
            true
        } else {
            val isOnWatchlist = !oldMovie.isOnWatchlist
            movieDao.update(movie.toEntity(isOnWatchlist))
            isOnWatchlist
        }
    }
}