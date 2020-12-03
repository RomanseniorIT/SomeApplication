package com.example.someapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapterViewHolder>() {

    val moviesList =
        mutableListOf(
            Movie(
                "Avengers: End Game",
                "Action, Adventure, Drama",
                125,
                4,
                137,
                13,
                false,
                R.drawable.ic_card_background
            ),
            Movie(
                "Tenet",
                "Action, Sci-Fi, Thriller",
                98,
                5,
                97,
                16,
                false,
                R.drawable.ic_tenet_poster
            ),
            Movie(
                "Black Widow",
                "Action, Adventure, Sci-Fi",
                38,
                4,
                102,
                13,
                false,
                R.drawable.ic_widow_poster
            ),
            Movie(
                "Wonder Woman 1984",
                "Action, Adventure, Fantasy",
                74,
                5,
                120,
                13,
                false,
                R.drawable.ic_woman_poster
            )
        )

    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_movie, parent, false)
        return MoviesListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesListAdapterViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            callback.startMovieDetailsFragment()
        }

        val item = moviesList[position]
        holder.setRate(item.rate)
        holder.genre.text = item.genre
        holder.title.text = item.title
        holder.reviews.text = "${item.reviews} reviews"
        holder.time.text = "${item.duration} min"
        holder.ageRate.text = "+${item.ageRate}"
        holder.like.setOnClickListener {
            if (!item.isLiked) {
                holder.like.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.ic_liked
                    )
                )
                item.isLiked = true
            } else {
                holder.like.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.ic_like
                    )
                )
                item.isLiked = false
            }
        }
        holder.poster.setImageDrawable(
            ContextCompat.getDrawable(
                holder.itemView.context,
                item.posterImage
            )
        )
    }

    fun initCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun startMovieDetailsFragment()
    }

}