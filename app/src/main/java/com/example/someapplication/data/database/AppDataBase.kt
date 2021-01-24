package com.example.someapplication.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.someapplication.MyApp
import com.example.someapplication.data.database.moviedetails.ActorsEntity
import com.example.someapplication.data.database.moviedetails.MovieDetailsDao
import com.example.someapplication.data.database.moviedetails.MovieDetailsEntity
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListDao
import com.example.someapplication.data.database.movieslist.MoviesListEntity

@Database(
    entities = [
        MoviesListEntity::class,
        GenresEntity::class,
        MovieDetailsEntity::class,
        ActorsEntity::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getMoviesListDbDao(): MoviesListDao

    abstract fun getMovieDetailsDbDao(): MovieDetailsDao

    companion object {
        private const val DATABASE_NAME = "movies.db"
        val instance: AppDataBase by lazy {
            Room.databaseBuilder(
                MyApp.context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}