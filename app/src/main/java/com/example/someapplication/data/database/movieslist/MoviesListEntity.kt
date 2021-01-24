package com.example.someapplication.data.database.movieslist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_list")
data class MoviesListEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "original_title") val title: String,
    @ColumnInfo(name = "poster_path") val poster: String,
    @ColumnInfo(name = "vote_average") val ratings: Float,
    @ColumnInfo(name = "vote_count") val numberOfRatings: Int,
    @ColumnInfo(name = "genre_ids") val genres: String,
    @ColumnInfo(name = "adult") val minimumAge: Boolean
)