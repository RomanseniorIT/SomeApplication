package com.example.someapplication.data.network

import com.example.someapplication.data.model.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @Headers(
        "Content-type: application/json"
    )
    @GET("movie/now_playing")
    suspend fun loadMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): MovieList

    @Headers(
        "Content-type: application/json"
    )
    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): ActorList

    @Headers(
        "Content-type: application/json"
    )
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): GenreList

    @Headers(
        "Content-type: application/json"
    )
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): MovieFull
}
