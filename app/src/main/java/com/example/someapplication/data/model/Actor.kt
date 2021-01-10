package com.example.someapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Actor(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val picture: String?
) : Parcelable

@Serializable
data class ActorList(
    @SerialName("cast") val actors: List<Actor>
)