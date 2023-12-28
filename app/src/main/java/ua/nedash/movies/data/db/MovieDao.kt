package ua.nedash.movies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getById(movieId: Int): MovieEntity?

    @Query("SELECT * FROM movies WHERE is_on_watchlist = 1")
    suspend fun getWatchlist(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :movieId AND is_on_watchlist = 1")
    suspend fun isOnWatchlist(movieId: Int): MovieEntity?
}
