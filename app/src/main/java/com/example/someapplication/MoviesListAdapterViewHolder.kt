package com.example.someapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

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
            callback.startMovieDetailsFragment(item.id)
        }

        setRate(item.rate)
        genre.text = item.genre
        title.text = item.title
        reviews.text = "${item.reviews} reviews"
        time.text = "${item.duration} min"
        ageRate.text = "+${item.ageRate}"
        like.setOnClickListener {
            if (!item.isLiked) {
                like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_liked
                    )
                )
                item.isLiked = true
            } else {
                like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_like
                    )
                )
                item.isLiked = false
            }
        }
        poster.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                item.posterImage
            )
        )
    }

    private fun setRate(rate: Int) {
        if (rate >= 1) {
            setRedStar(starOne)
        } else {
            setGrayStar(starOne)
        }

        if (rate >= 2) {
            setRedStar(starTwo)
        } else {
            setGrayStar(starTwo)
        }

        if (rate >= 3) {
            setRedStar(starThree)
        } else {
            setGrayStar(starThree)
        }

        if (rate >= 4) {
            setRedStar(starFour)
        } else {
            setGrayStar(starFour)
        }

        if (rate == 5) {
            setRedStar(starFive)
        } else {
            setGrayStar(starFive)
        }

        if (rate > 5)
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
}