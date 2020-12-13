package com.example.someapplication.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.someapplication.R
import com.example.someapplication.data.Genre
import com.example.someapplication.data.Movie

class MoviesListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var title = itemView.findViewById<TextView>(R.id.tv_title_card)
    private var starOne = itemView.findViewById<ImageView>(R.id.iv_star_one_card)
    private var starTwo = itemView.findViewById<ImageView>(R.id.iv_star_two_card)
    private var starThree = itemView.findViewById<ImageView>(R.id.iv_star_three_card)
    private var starFour = itemView.findViewById<ImageView>(R.id.iv_star_four_card)
    private var starFive = itemView.findViewById<ImageView>(R.id.iv_star_five_card)
    private var genre = itemView.findViewById<TextView>(R.id.tv_genre)
    private var reviews = itemView.findViewById<TextView>(R.id.tv_reviews_card)
    private var poster = itemView.findViewById<ImageView>(R.id.iv_poster_card)
    private var time = itemView.findViewById<TextView>(R.id.tv_time_card)
    private var like = itemView.findViewById<ImageView>(R.id.iv_like_card)
    private var ageRate = itemView.findViewById<TextView>(R.id.tv_age_card)

    fun onBind(item: Movie, callback: MoviesListAdapter.Callback) {
        itemView.setOnClickListener {
            callback.startMovieDetailsFragment(item)
        }
        genre.text = setGenres(item.genres)
        setRate(item.ratings)
        title.text = item.title
        reviews.text = "${item.numberOfRatings} reviews"
        time.text = "${item.runtime} min"
        ageRate.text = "+${item.minimumAge}"
        like.setOnClickListener {
            like.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_liked
                )
            )
        }
        val posterUrl = item.poster
        Glide.with(itemView)
            .load(posterUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_download)
            .into(poster)
    }

    private fun setRate(rate: Float) {
        if (rate >= 2.0F) {
            setRedStar(starOne)
        } else {
            setGrayStar(starOne)
        }

        if (rate >= 4.0F) {
            setRedStar(starTwo)
        } else {
            setGrayStar(starTwo)
        }

        if (rate >= 6.0F) {
            setRedStar(starThree)
        } else {
            setGrayStar(starThree)
        }

        if (rate >= 8.0F) {
            setRedStar(starFour)
        } else {
            setGrayStar(starFour)
        }

        if (rate == 10.0F) {
            setRedStar(starFive)
        } else {
            setGrayStar(starFive)
        }

        if (rate > 10)
            throw IllegalStateException("Illegal rating value")
    }

    private fun setGrayStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_gray_star_card
            )
        )
    }

    private fun setRedStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_red_star_card
            )
        )
    }

    private fun setGenres(genres: List<Genre>): String{
        var genresStr = ""
        for (i in genres.indices){
            genresStr += if (i == genres.size-1){
                genres[i].name
            } else {
                "${genres[i].name}, "
            }
        }
        return genresStr
    }
}