package com.example.someapplication.ui.moviedetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.model.Actor
import com.example.someapplication.data.model.MovieFull
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private val _movieLiveData = MutableLiveData<MovieFull>()
    val movieLiveData: LiveData<MovieFull> get() = _movieLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData
    private val repository = MoviesRepository()

    fun getMovies(context: Context, movieId: Int) {
        viewModelScope.launch {
            _movieLiveData.value = repository.getMovie(context, movieId)
        }
    }

    fun getActors(context: Context, movieId: Int) {
        viewModelScope.launch {
            _actorsLiveData.value = repository.getActors(context, movieId)
        }
    }

}