package com.example.popularmovies_Level6_Task2.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies_Level6_Task2.R
import com.example.popularmovies_Level6_Task2.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
/**
 * @author Raeef Ibrahim
 * 500766393
 */
class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var context: Context

    private val correction = 1
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    // Returns the size of the list
    override fun getItemCount(): Int {
        return movies.size
    }

    // Called by RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }

        fun bind(movie: Movie) {
            itemView.tvNumber.text =
                context.getString(R.string.movie_number, (adapterPosition + correction).toString())
            Glide.with(context).load(imageBaseUrl + movie.poster).into(itemView.ivMovieImage)
        }
    }


}