package ua.nedash.movies.use_case

import ua.nedash.movies.data.db.MovieRepository
import ua.nedash.movies.data.db.toMovies
import ua.nedash.movies.data.model.Movies

class GetWatchlist(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): Movies {
        return movieRepository.getWatchlist().filter { it.isOnWatchlist }.toMovies()
    }

}