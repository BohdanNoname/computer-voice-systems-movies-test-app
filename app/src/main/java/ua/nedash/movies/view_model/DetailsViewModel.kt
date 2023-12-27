package ua.nedash.movies.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import ua.nedash.movies.data.model.Movie
import ua.nedash.movies.use_case.MovieUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    private val _isOnWatchlist = MutableStateFlow(false)
    val isOnWatchlist = _isOnWatchlist.asStateFlow()

    fun checkIsOnWatchlist(movie: Movie) = flow<Boolean> {
        val isOnWatchlist = useCase.isOnWatchlist(movie.id)
        _isOnWatchlist.emit(isOnWatchlist)
    }.launchIn(scope)

    fun toWatchlist(movie: Movie) = flow<Unit> {
        _isOnWatchlist.emit(useCase.toWatchlist(movie))
    }.launchIn(scope)
}