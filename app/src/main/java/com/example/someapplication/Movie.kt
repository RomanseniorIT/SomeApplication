package com.example.someapplication

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val genre: String,
    var reviews: Int,
    var rate: Int,
    val duration: Int,
    val ageRate: Int,
    var isLiked: Boolean = false,
    @DrawableRes val posterImage: Int
)