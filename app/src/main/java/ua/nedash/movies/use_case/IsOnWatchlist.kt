package ua.nedash.movies.use_case

import ua.nedash.movies.data.db.MovieRepository

class IsOnWatchlist(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): Boolean {
        val movie = movieRepository.isOnWatchlist(movieId)
        return movie != null && movie.isOnWatchlist
    }

}