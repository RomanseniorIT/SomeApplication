package com.example.someapplication.ui.movies

import android.app.Application
import androidx.lifecycle.*
import com.example.someapplication.data.MovieNotifications
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import kotlinx.coroutines.launch

class MoviesListViewModel @JvmOverloads constructor(application: Application) : AndroidViewModel(application) {
    private val _moviesLiveData = MutableLiveData<List<MoviesListEntity>>()
    private val _genresLiveData = MutableLiveData<List<GenresEntity>>()
    private val notifications = MovieNotifications(application)
    private val repository = MoviesRepository(notifications)

    val genresLiveData: LiveData<List<GenresEntity>> get() = _genresLiveData
    val moviesLiveData: LiveData<List<MoviesListEntity>> get() = _moviesLiveData

    fun getMovies() {
        viewModelScope.launch {
            repository.loadMovies()
        }
        getCachedMovies()
    }

    fun getGenres() {
        viewModelScope.launch {
            repository.getGenres()
        }
        getCachedGenres()
    }

    private fun getCachedMovies() {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadCachedMovies()
        }
    }

    private fun getCachedGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getCachedGenres()
        }
    }
}