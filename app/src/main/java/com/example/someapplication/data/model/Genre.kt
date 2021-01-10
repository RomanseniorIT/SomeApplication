package com.example.someapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Genre(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
): Parcelable

@Serializable
data class GenreList(
    @SerialName("genres") val genres: List<Genre>
)