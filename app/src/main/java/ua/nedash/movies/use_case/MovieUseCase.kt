package ua.nedash.movies.use_case

data class MovieUseCase(
    val toWatchlist: ToWatchlist,
    val isOnWatchlist: IsOnWatchlist,
    val getWatchlist: GetWatchlist
)
