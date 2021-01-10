package com.example.someapplication.ui.movies

import android.content.Context
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
    val moviesLiveData: LiveData<List<MoviePreview>> get() = _moviesLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData
    private val repository = MoviesRepository()

    fun getMovies(context: Context) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadMovies(context)
        }
    }

    fun getGenres(context: Context) {
        viewModelScope.launch {
            _genresLiveData.value = repository.getGenres(context)
        }
    }
}