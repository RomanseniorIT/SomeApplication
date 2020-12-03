package com.example.someapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MoviesListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title = itemView.findViewById<TextView>(R.id.tv_title_card)
    var starOne = itemView.findViewById<ImageView>(R.id.iv_star_one_card)
    var starTwo = itemView.findViewById<ImageView>(R.id.iv_star_two_card)
    var starThree = itemView.findViewById<ImageView>(R.id.iv_star_three_card)
    var starFour = itemView.findViewById<ImageView>(R.id.iv_star_four_card)
    var starFive = itemView.findViewById<ImageView>(R.id.iv_star_five_card)
    var genre = itemView.findViewById<TextView>(R.id.tv_genre)
    var reviews = itemView.findViewById<TextView>(R.id.tv_reviews_card)
    var poster = itemView.findViewById<ImageView>(R.id.iv_poster_card)
    var time = itemView.findViewById<TextView>(R.id.tv_time_card)
    var like = itemView.findViewById<ImageView>(R.id.iv_like_card)
    var ageRate = itemView.findViewById<TextView>(R.id.tv_age_card)

    fun setRate(rate: Int) {
        when (rate) {
            1 -> {
                starOne.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starTwo.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starThree.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starFour.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starFive.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )

            }
            2 -> {
                starOne.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starTwo.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starThree.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starFour.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starFive.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
            }
            3 -> {
                starOne.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starTwo.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starThree.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starFour.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
                starFive.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
            }
            4 -> {
                starOne.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starTwo.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starThree.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starFour.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starFive.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_gray_star
                    )
                )
            }
            5 -> {
                starOne.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starTwo.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starThree.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starFour.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
                starFive.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_red_star
                    )
                )
            }
            else -> throw IllegalStateException("Illegal rating value")
        }
    }
}