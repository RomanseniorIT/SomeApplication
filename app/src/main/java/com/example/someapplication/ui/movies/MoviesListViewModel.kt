package com.example.someapplication.ui.movies

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

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<MoviesListEntity>>()
    private val _genresLiveData = MutableLiveData<List<GenresEntity>>()
    private val repository = MoviesRepository()

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
    }

    private fun getGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getGenres()
        }
    }

    fun getCachedMovies(page: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.loadCachedMovies(page)
        }
        getMovies(page)
    }

    fun getCachedGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getCachedGenres()
        }
        getGenres()
    }
}