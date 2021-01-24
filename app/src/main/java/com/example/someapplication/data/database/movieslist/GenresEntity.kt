package com.example.someapplication.data.database.movieslist

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "genres_list")
data class GenresEntity(
    @PrimaryKey @ColumnInfo(name = "genreId") val id: Int,
    @ColumnInfo(name = "genre") val name: String
)