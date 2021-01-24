package com.example.someapplication.data.database.moviedetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "original_title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "backdrop_path") val backdrop: String,
    @ColumnInfo(name = "vote_average") val ratings: Float,
    @ColumnInfo(name = "vote_count") val numberOfRatings: Int,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "adult") val minimumAge: Boolean
)