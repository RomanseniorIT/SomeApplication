package com.example.someapplication.ui.moviedetails

import android.app.Application
import androidx.lifecycle.*
import com.example.someapplication.data.*
import com.example.someapplication.data.model.MovieWithActors
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class MovieDetailsViewModel@JvmOverloads constructor(application: Application) : AndroidViewModel(application), BaseUseCase.ResultListener<MovieWithActors> {
    private val _movieLiveData = MutableLiveData<MovieWithActors?>()
    private val notifications = MovieNotifications(application)
    private val repository = MoviesRepository(notifications)
    private val useCase = GetMovieUseCase(this, repository)
    private val cachedUseCase = GetCachedMovieUseCase(this, repository)

    val movieLiveData: LiveData<MovieWithActors?> get() = _movieLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            useCase.execute(viewModelScope, movieId)
        }
    }

    fun getCachedMovie(movieId: Int) {
        viewModelScope.launch {
            cachedUseCase.execute(viewModelScope, movieId)
        }
        getMovie(movieId)
    }

    override fun onSuccess(result: MovieWithActors) {
        _movieLiveData.value = result
    }

    override fun onFailed(exception: Exception) {
        if (exception is UnknownHostException || exception is ConnectException) {
            _movieLiveData.value = null
        }
    }
}