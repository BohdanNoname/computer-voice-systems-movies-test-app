package ua.nedash.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.nedash.movies.data.db.MovieRepository
import ua.nedash.movies.use_case.GetWatchlist
import ua.nedash.movies.use_case.IsOnWatchlist
import ua.nedash.movies.use_case.MovieUseCase
import ua.nedash.movies.use_case.ToWatchlist
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieUseCase(movieRepository: MovieRepository): MovieUseCase {
        return MovieUseCase(
            toWatchlist = ToWatchlist(movieRepository),
            isOnWatchlist = IsOnWatchlist(movieRepository),
            getWatchlist = GetWatchlist(movieRepository)
        )
    }
}