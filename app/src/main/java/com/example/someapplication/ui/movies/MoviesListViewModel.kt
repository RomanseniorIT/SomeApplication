package com.example.someapplication.ui.movies

import android.app.Application
import androidx.lifecycle.*
import com.example.someapplication.data.MovieNotifications
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.someapplication.data.MoviesPagingDataSource
import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoviesListViewModel @JvmOverloads constructor(application: Application) : AndroidViewModel(application) {
    private val _moviesLiveData = MutableLiveData<List<MoviesListEntity>>()
    private val _genresLiveData = MutableLiveData<List<GenresEntity>>()
    private val notifications = MovieNotifications(application)
    private val repository = MoviesRepository(notifications)

    val genresLiveData: LiveData<List<GenresEntity>> get() = _genresLiveData
    val moviesLiveData: LiveData<List<MoviesListEntity>> get() = _moviesLiveData

    val movies: Flow<PagingData<MoviesListEntity>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { MoviesPagingDataSource(repository) }
        ).flow.cachedIn(viewModelScope)

    private fun getMovies(page: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadMovies(page)
        }
        getCachedMovies()
    }

    private fun getGenres() {
        viewModelScope.launch {
            repository.getGenres()
        }
        getCachedGenres()
    }

    private fun getCachedMovies(page: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadCachedMovies(page)
        }
    }

    private fun getCachedGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getCachedGenres()
        }
    }
}