package ua.nedash.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.nedash.movies.data.db.MovieDatabase
import ua.nedash.movies.data.db.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.getDataBase(context)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieDatabase: MovieDatabase) = MovieRepository(movieDatabase)
}