package com.example.someapplication.data.database.movieslist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesListDao {
    @Query("SELECT * FROM movies_list")
    fun getCashedMoviesList(): List<MoviesListEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMoviesList(moviesList: List<MoviesListEntity>): List<Long>

    @Query("DELETE FROM movies_list")
    fun deleteCachedMoviesList()

    @Query("SELECT * FROM genres_list")
    fun getCashedGenresList(): List<GenresEntity>

    @Insert
    fun saveGenresList(genreList: List<GenresEntity>)

    @Query("DELETE FROM genres_list")
    fun deleteCachedGenresList()
}