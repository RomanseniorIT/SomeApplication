package com.example.someapplication.ui.moviedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.someapplication.data.BaseUseCase
import com.example.someapplication.data.GetCachedMovieUseCase
import com.example.someapplication.data.GetMovieUseCase
import com.example.someapplication.data.MovieNotifications
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.model.MovieWithActors
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class MovieDetailsViewModel @JvmOverloads constructor(application: Application) : AndroidViewModel(application), BaseUseCase.ResultListener<MovieWithActors> {
    private val _movieLiveData = MutableLiveData<MovieWithActors?>()
    private val _uiProgressLiveData = MutableLiveData<Boolean>()
    private val notifications = MovieNotifications(application)
    private val repository = MoviesRepository(notifications)
    private val useCase = GetMovieUseCase(this, repository)
    private val cachedUseCase = GetCachedMovieUseCase(this, repository)
    private var movieId = 0

    val movieLiveData: LiveData<MovieWithActors?> get() = _movieLiveData
    val uiProgressLiveData: LiveData<Boolean> get() = _uiProgressLiveData

    fun getMovie(movieId: Int) {
        this.movieId = movieId
        _uiProgressLiveData.value = true
        viewModelScope.launch {
            useCase.execute(viewModelScope, movieId)
        }
    }

    private fun getCachedMovie(movieId: Int) {
        viewModelScope.launch {
            cachedUseCase.execute(viewModelScope, movieId)
        }
    }

    override fun onSuccess(result: MovieWithActors, cacheResult: Boolean) {
        if (cacheResult) {
            _uiProgressLiveData.value = false
            _movieLiveData.value = result
        } else {
            getCachedMovie(movieId)
        }
    }

    override fun onFailed(exception: Exception, cacheResult: Boolean) {
        if (cacheResult) {
            _uiProgressLiveData.value = false
        } else {
            getCachedMovie(movieId)
            if (exception is UnknownHostException || exception is ConnectException) {
                _movieLiveData.value = null
            }
        }
    }
}