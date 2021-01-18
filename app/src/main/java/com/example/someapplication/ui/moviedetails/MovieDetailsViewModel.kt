package com.example.someapplication.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.someapplication.data.BaseUseCase
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.GetMovieUseCase
import com.example.someapplication.data.model.MovieWithActors
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class MovieDetailsViewModel : ViewModel(), BaseUseCase.ResultListener<MovieWithActors> {
    private val _movieLiveData = MutableLiveData<MovieWithActors?>()
    private val repository = MoviesRepository()
    private val useCase = GetMovieUseCase(this, repository)

    val movieLiveData: LiveData<MovieWithActors?> get() = _movieLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            useCase.execute(viewModelScope, movieId)
        }
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