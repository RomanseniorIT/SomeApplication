package com.example.someapplication.data.database.moviedetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    fun getCashedMovieDetails(movieId: Int): MovieDetailsEntity

    @Insert
    fun saveMovieDetails(movieDetails: MovieDetailsEntity)

    @Query("DELETE FROM movie_details WHERE id = :movieId")
    fun deleteCachedMovieDetails(movieId: Int)

    @Query("SELECT * FROM actors_list WHERE movie_id = :movieId")
    fun getCashedActorsList(movieId: Int): List<ActorsEntity>

    @Insert
    fun saveActorsList(actorsList: List<ActorsEntity>)

    @Query("DELETE FROM actors_list WHERE movie_id = :movieId")
    fun deleteCachedActorsList(movieId: Int)
}