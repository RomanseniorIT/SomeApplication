package com.example.someapplication.data

import androidx.paging.PagingSource
import com.example.someapplication.BuildConfig
import com.example.someapplication.Mapper
import com.example.someapplication.data.database.AppDataBase
import com.example.someapplication.data.database.moviedetails.ActorsEntity
import com.example.someapplication.data.database.moviedetails.MovieDetailsEntity
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import com.example.someapplication.data.model.Actor
import com.example.someapplication.data.model.Genre
import com.example.someapplication.data.model.MovieFull
import com.example.someapplication.data.model.MoviePreview
import com.example.someapplication.data.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository {

    private val api = NetworkModule().provideApiService()
    private val daoMovieDetails = AppDataBase.instance.getMovieDetailsDbDao()
    private val daoMoviesList = AppDataBase.instance.getMoviesListDbDao()

    suspend fun loadMovies(page: Int): List<MoviesListEntity>? {
        var moviePreviewListDto: List<MoviePreview>? = null
        var moviesResult: List<MoviesListEntity>? = null
        try {
            moviePreviewListDto = withContext(Dispatchers.IO) {
                api.loadMovies(BuildConfig.API_KEY, LANG, page).results
            }
            moviesResult = Mapper.mapMoviesListToDb(moviePreviewListDto, page)
            withContext(Dispatchers.IO) {
                daoMoviesList.deleteCachedMoviesList()
                daoMoviesList.saveMoviesList(moviesResult)
            }
        } catch (e: Exception) {
        }
        return moviesResult
    }

    suspend fun getGenres(): List<GenresEntity>? {
        var genresDto: List<Genre>? = null
        var genresResult: List<GenresEntity>? = null
        try {
            genresDto = withContext(Dispatchers.IO) {
                api.getGenres(BuildConfig.API_KEY, LANG).genres
            }
            genresResult = Mapper.mapGenresToDb(genresDto)
            withContext(Dispatchers.IO) {
                daoMoviesList.deleteCachedGenresList()
                daoMoviesList.saveGenresList(genresResult)
            }
        } catch (e: Exception) {
        }
        return genresResult
    }

    suspend fun loadCachedMovies(page: Int): List<MoviesListEntity>? {
        return withContext(Dispatchers.IO) {
            daoMoviesList.getCashedMoviesList(page)
        }
    }

    suspend fun getCachedGenres(): List<GenresEntity>? {
        return withContext(Dispatchers.IO) {
            daoMoviesList.getCashedGenresList()
        }
    }

    suspend fun getMovie(movieId: Int): MovieDetailsEntity? {
        var movieFull: MovieFull? = null
        var movieDetailsEntity: MovieDetailsEntity? = null
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getMovie(movieId, BuildConfig.API_KEY, LANG)
            }
            movieDetailsEntity = Mapper.mapDetailsToDb(movieFull)
            withContext(Dispatchers.IO) {
                daoMovieDetails.deleteCachedMovieDetails(movieId)
                daoMovieDetails.saveMovieDetails(movieDetailsEntity)
            }
        } catch (e: Exception) {
            throw e
        }
        return movieDetailsEntity
    }

    suspend fun getActors(movieId: Int): List<ActorsEntity>? {
        var actors: List<Actor>? = null
        var actorsEntityList: List<ActorsEntity>? = null
        try {
            actors = withContext(Dispatchers.IO) {
                api.getActors(movieId, BuildConfig.API_KEY, LANG).actors
            }
            actorsEntityList = Mapper.mapActorsToDb(actors, movieId)
            withContext(Dispatchers.IO) {
                daoMovieDetails.deleteCachedActorsList(movieId)
                daoMovieDetails.saveActorsList(actorsEntityList)
            }
        } catch (e: Exception) {
            throw e
        }
        return actorsEntityList
    }

    suspend fun getCachedMovie(movieId: Int): MovieDetailsEntity? {
        return withContext(Dispatchers.IO) {
            daoMovieDetails.getCashedMovieDetails(movieId)
        }
    }

    suspend fun getCachedActors(movieId: Int): List<ActorsEntity>? {
        return withContext(Dispatchers.IO) {
            daoMovieDetails.getCashedActorsList(movieId)
        }
    }

    companion object {
        private const val LANG = "en-US"
        private const val PAGE = 1
    }
}