package com.example.someapplication.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.model.Genre
import com.example.someapplication.data.model.MoviePreview
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<MoviePreview>>()
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    private val repository = MoviesRepository()

    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData
    val moviesLiveData: LiveData<List<MoviePreview>> get() = _moviesLiveData

    fun getMovies() {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadMovies()
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getGenres()
        }
    }
}