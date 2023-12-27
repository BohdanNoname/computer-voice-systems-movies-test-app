package ua.nedash.movies.data.db

class MovieRepository(
    private val movieDatabase: MovieDatabase
) {
    private val movieDao = movieDatabase.movieDao()

    suspend fun insert(movie: MovieEntity) = movieDao.insert(movie)
    suspend fun delete(movie: MovieEntity) = movieDao.delete(movie)
    suspend fun update(movie: MovieEntity) = movieDao.update(movie)
    suspend fun getAll(): List<MovieEntity> = movieDao.getAll()
    suspend fun getWatchlist(): List<MovieEntity> = movieDao.getWatchlist()
    suspend fun getById(movieId: Int): MovieEntity? = movieDao.getById(movieId)
    suspend fun isOnWatchlist(movieId: Int): MovieEntity? = movieDao.isOnWatchlist(movieId)
}