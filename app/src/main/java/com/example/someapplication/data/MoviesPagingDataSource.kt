package com.example.someapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.someapplication.data.database.movieslist.MoviesListEntity

class MoviesPagingDataSource(private val repository: MoviesRepository) :
    PagingSource<Int, MoviesListEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesListEntity> {
        val currentLoadingPageKey = params.key ?: 1
        val movies = repository.loadMovies(currentLoadingPageKey) ?: emptyList()
        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
        return LoadResult.Page(movies, prevKey, currentLoadingPageKey+1)
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesListEntity>): Int? {
        return state.anchorPosition
    }
}