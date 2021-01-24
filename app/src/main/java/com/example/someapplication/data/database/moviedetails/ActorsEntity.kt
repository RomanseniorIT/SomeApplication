package com.example.someapplication.data.database.moviedetails

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "actors_list")
data class ActorsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "profile_path") val picture: String?,
    @ColumnInfo(name = "movie_id") val movieId: Int?,
) : Parcelable