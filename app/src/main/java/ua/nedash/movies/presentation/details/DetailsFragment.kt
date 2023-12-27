package ua.nedash.movies.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.nedash.movies.R
import ua.nedash.movies.databinding.FragmentDetailsBinding
import ua.nedash.movies.extensions.openSite
import ua.nedash.movies.extensions.setImageUrlWithGlide
import ua.nedash.movies.view_model.DetailsViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.checkIsOnWatchlist(args.movie)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isOnWatchlist()
        initUi()
        initListeners()
    }

    private fun isOnWatchlist() {
        lifecycleScope.launch {
            viewModel.isOnWatchlist.collectLatest {
                val text = if (!it) R.string.add_to_watchlist else R.string.remove_from_watchlist

                binding.btnAddToWatchlist.text = requireContext().getString(text)
            }
        }
    }

    private fun initUi() {
        with(binding) {
            ivCoverArt.setImageUrlWithGlide(args.movie.coverArtUrl)
            tvTitle.text = args.movie.title
            tvRating.text = args.movie.rating
            tvDescription.text = args.movie.description

            val genres = args.movie.genre.toString().removePrefix("[").removeSuffix("]")
            tvGenres.text = genres

            val releaseYear = args.movie.releaseDate.takeLast(4)
            val releaseDate = "$releaseYear, ${args.movie.releaseDate.dropLast(4)}"
            tvReleasedDate.text = releaseDate
        }
    }

    private fun initListeners() {
        with(binding) {
            llTitle.setOnClickListener {
                findNavController().popBackStack()
            }

            btnAddToWatchlist.setOnClickListener {
                viewModel.toWatchlist(args.movie)
            }

            btnWatchTrailer.setOnClickListener {
                openSite(args.movie.trailerLink)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}