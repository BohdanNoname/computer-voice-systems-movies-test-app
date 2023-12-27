package ua.nedash.movies.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.nedash.movies.R
import ua.nedash.movies.data.model.Movie
import ua.nedash.movies.data.model.Movies
import ua.nedash.movies.databinding.FragmentCatalogBinding
import ua.nedash.movies.extensions.getListFromJsonResource
import ua.nedash.movies.presentation.catalog.adapter.MoviesAdapter
import ua.nedash.movies.view_model.CatalogViewModel
import ua.nedash.movies.view_model.CatalogViewModel.SortType

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        viewModel.getWatchlist()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMoviesRecyclerView()
        collectWatchlist()
        collectSortType()
        initListeners()
    }

    private fun collectWatchlist() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.watchlist.collectLatest { watchlist ->
                val allMovies =
                    (requireContext().getListFromJsonResource(R.raw.movies) as Movies).movies

                watchlist.movies.map {
                    allMovies.find { movie -> movie.id == it.id }?.isOnWatchlist = it.isOnWatchlist
                }

                setSortedMovies(allMovies, viewModel.sortType.value)
            }
        }
    }

    private fun collectSortType() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.sortType.collectLatest { sortType ->
                val movies = (binding.rvMovies.adapter as MoviesAdapter).currentList
                if (movies.isNotEmpty() || sortType != SortType.NONE)
                    setSortedMovies(movies, sortType)
            }
        }
    }

    private fun setSortedMovies(movies: List<Movie>, sortType: SortType) {
        when (sortType) {
            SortType.TITLE -> {
                (binding.rvMovies.adapter as MoviesAdapter).submitList(movies.sortedBy { movie -> movie.title })
            }

            SortType.RELEASE_DATE -> {
                (binding.rvMovies.adapter as MoviesAdapter).submitList(movies.sortedBy { movie -> movie.releaseDateInMillis })
            }

            else -> {
                (binding.rvMovies.adapter as MoviesAdapter).submitList(movies)
            }
        }
    }

    private fun initMoviesRecyclerView() {
        binding.rvMovies.setHasFixedSize(true)

        binding.rvMovies.adapter = MoviesAdapter(onMovieClick = { movie -> onMovieClick(movie) })
        binding.rvMovies.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    private fun onMovieClick(movie: Movie) {
        val direction = CatalogFragmentDirections.actionCatalogFragmentToDetailsFragment(movie)

        findNavController().navigate(direction)
    }

    private fun initListeners() {
        with(binding) {
            tvSortType.setOnClickListener {
                val direction = CatalogFragmentDirections.actionCatalogFragmentToSortTypeFragment()

                findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}