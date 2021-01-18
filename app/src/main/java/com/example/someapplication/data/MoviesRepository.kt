package com.example.someapplication.data

import com.example.someapplication.data.model.Actor
import com.example.someapplication.data.model.Genre
import com.example.someapplication.data.model.MovieFull
import com.example.someapplication.data.model.MoviePreview
import com.example.someapplication.data.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository {

    private val api = NetworkModule().provideApiService()

    suspend fun loadMovies(): List<MoviePreview>? {
        var moviePreviewList: List<MoviePreview>? = null
        try {
            moviePreviewList = withContext(Dispatchers.IO) {
                api.loadMovies(KEY, LANG, PAGE).results
            }
        } catch (e: Exception) {
        }
        return moviePreviewList
    }

    suspend fun getGenres(): List<Genre>? {
        var genres: List<Genre>? = null
        try {
            genres = withContext(Dispatchers.IO) {
                api.getGenres(KEY, LANG).genres
            }
        } catch (e: Exception) {
        }
        return genres
    }

    suspend fun getMovie(movieId: Int): MovieFull? {
        var movieFull: MovieFull? = null
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getMovie(movieId, KEY, LANG)
            }
        } catch (e: Exception) {
            throw e
        }
        return movieFull
    }

    suspend fun getActors(movieId: Int): List<Actor>? {
        var actors: List<Actor>? = null
        try {
            actors = withContext(Dispatchers.IO) {
                api.getActors(movieId, KEY, LANG).actors
            }
        } catch (e: Exception) {
            throw e
        }
        return actors
    }

    companion object {
        private const val LANG = "en-US"
        private const val KEY = "8d22ad8ff7d89a9f51399571d962eedb"
        private const val PAGE = 1
    }
}