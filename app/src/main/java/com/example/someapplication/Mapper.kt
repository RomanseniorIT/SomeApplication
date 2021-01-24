package com.example.someapplication

import com.example.someapplication.data.database.moviedetails.ActorsEntity
import com.example.someapplication.data.database.moviedetails.MovieDetailsEntity
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import com.example.someapplication.data.model.Actor
import com.example.someapplication.data.model.Genre
import com.example.someapplication.data.model.MovieFull
import com.example.someapplication.data.model.MoviePreview
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Mapper {
    fun mapMoviesListToDb(moviesList: List<MoviePreview>): List<MoviesListEntity> {
        return moviesList.map {
            val genres = Json.encodeToString(it.genres)
            MoviesListEntity(
                it.id,
                it.title,
                it.poster,
                it.ratings,
                it.numberOfRatings,
                genres,
                it.minimumAge
            )
        }
    }

    fun mapGenresToDb(genreList: List<Genre>): List<GenresEntity> {
        return genreList.map {
            GenresEntity(
                it.id,
                it.name
            )
        }
    }

    fun mapDetailsToDb(movieDetails: MovieFull): MovieDetailsEntity {

        val genres = Json.encodeToString(movieDetails.genres)
        return MovieDetailsEntity(
            movieDetails.id,
            movieDetails.title,
            movieDetails.overview,
            movieDetails.backdrop,
            movieDetails.ratings,
            movieDetails.numberOfRatings,
            genres,
            movieDetails.minimumAge
        )
    }

    fun mapActorsToDb(actorsList: List<Actor>, movieId: Int): List<ActorsEntity> {
        return actorsList.map {
            ActorsEntity(
                it.id,
                it.name,
                it.picture,
                movieId
            )
        }
    }
}