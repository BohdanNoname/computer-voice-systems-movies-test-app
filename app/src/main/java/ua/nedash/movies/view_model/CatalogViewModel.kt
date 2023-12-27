package ua.nedash.movies.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import ua.nedash.movies.data.model.Movies
import ua.nedash.movies.use_case.MovieUseCase
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    private val _watchlist = MutableSharedFlow<Movies>()
    val watchlist = _watchlist.asSharedFlow()

    private val _sortType = MutableStateFlow(SortType.NONE)
    val sortType = _sortType.asStateFlow()

    fun getWatchlist() = flow<Unit> {
        val movies = useCase.getWatchlist()
        _watchlist.emit(movies)
    }.launchIn(scope)

    fun setSortType(sortType: SortType) {
        _sortType.value = sortType
    }

    enum class SortType {
        TITLE,
        RELEASE_DATE,
        NONE
    }
}