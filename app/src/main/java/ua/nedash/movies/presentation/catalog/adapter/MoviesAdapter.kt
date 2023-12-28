package ua.nedash.movies.presentation.catalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.nedash.movies.R
import ua.nedash.movies.data.model.Movie
import ua.nedash.movies.databinding.ItemMovieBinding
import ua.nedash.movies.extensions.setImageUrlWithGlide

class MoviesAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MovieDiffCallback), View.OnClickListener {

    override fun onClick(v: View?) {
        val movie = v?.tag as? Movie ?: return

        when (v.id) {
            R.id.cl_item_movie -> onMovieClick(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.root.setOnClickListener(this)

        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        init {
//            binding.root.setOnClickListener {
//                onMovieClick(currentList[bindingAdapterPosition])
//            }
//        }

        private val context = binding.root.context

        fun bind(movie: Movie) {
            with(binding) {
                clItemMovie.tag = movie
                tvIsOnWatchlist.visibility = if (movie.isOnWatchlist) View.VISIBLE else View.GONE

                ivCoverArt.setImageUrlWithGlide(movie.coverArtUrl)

                val movieYear = movie.releaseDate.takeLast(4)
                tvTitleAndYear.text =
                    context.getString(R.string.title_and_year, movie.title, movieYear)

                val genres = movie.genre.joinToString()
                tvTimeAndGenre.text =
                    context.getString(R.string.time_and_genre, movie.duration, genres)
            }
        }
    }

    private object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}