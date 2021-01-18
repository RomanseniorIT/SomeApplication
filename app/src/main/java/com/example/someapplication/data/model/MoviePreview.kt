package com.example.someapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MoviePreview(
    @SerialName("id") val id: Int,
    @SerialName("original_title") val title: String,
    @SerialName("poster_path") val poster: String,
    @SerialName("vote_average") val ratings: Float,
    @SerialName("vote_count") val numberOfRatings: Int,
    @SerialName("genre_ids") val genres: List<Int>,
    @SerialName("adult") val minimumAge: Boolean
): Parcelable

@Serializable
data class MovieList(
    @SerialName("results") val results: List<MoviePreview>
)

@Serializable
data class MovieFull(
    @SerialName("id") val id: Int,
    @SerialName("original_title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("backdrop_path") val backdrop: String,
    @SerialName("vote_average") val ratings: Float,
    @SerialName("vote_count") val numberOfRatings: Int,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("adult") val minimumAge: Boolean
)

data class MovieWithActors(
    val movieFull: MovieFull?,
    val actors: List<Actor>?
)