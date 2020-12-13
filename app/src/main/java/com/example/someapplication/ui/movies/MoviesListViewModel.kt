package com.example.someapplication.ui.movies

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.someapplication.data.Movie
import com.example.someapplication.data.loadMovies
import kotlinx.coroutines.launch

class MoviesListViewModel: ViewModel() {
    private val _items = MutableLiveData<List<Movie>>()
    val items: LiveData<List<Movie>> get() = _items

    fun getMovies(context: Context){
        viewModelScope.launch {
            _items.value = loadMovies(context)
        }
    }
}