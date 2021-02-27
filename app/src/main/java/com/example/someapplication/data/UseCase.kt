package com.example.someapplication.data

import com.example.someapplication.data.model.MovieWithActors
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseUseCase<T, P>(protected val resultListener: ResultListener<T>) {

    open val dispatcher: CoroutineDispatcher = Dispatchers.Default

    abstract suspend fun execute(scope: CoroutineScope, param: P)

    interface ResultListener<T> {
        fun onSuccess(result: T, cacheResult: Boolean)

        fun onFailed(exception: Exception, cacheResult: Boolean)
    }
}

class GetMovieUseCase(
    listener: ResultListener<MovieWithActors>,
    private val repository: MoviesRepository
) : BaseUseCase<MovieWithActors, Int>(listener) {

    override suspend fun execute(scope: CoroutineScope, param: Int) {
        scope.launch(dispatcher) {
            try {
                val movieFull = repository.getMovie(param)
                val actors = repository.getActors(param)
                val movieWithActors = MovieWithActors(movieFull, actors)
                scope.launch(Dispatchers.Main) {
                    resultListener.onSuccess(movieWithActors, false)
                }
            } catch (exception: Exception) {
                scope.launch(Dispatchers.Main) {
                    resultListener.onFailed(exception, false)
                }
            }
        }
    }
}

class GetCachedMovieUseCase(
    listener: BaseUseCase.ResultListener<MovieWithActors>,
    private val repository: MoviesRepository
) : BaseUseCase<MovieWithActors, Int>(listener) {

    override suspend fun execute(scope: CoroutineScope, param: Int) {
        scope.launch(dispatcher) {
            try {
                val movieEntity = repository.getCachedMovie(param)
                val actors = repository.getCachedActors(param)
                val movieWithActors = MovieWithActors(movieEntity, actors)
                scope.launch(Dispatchers.Main) {
                    resultListener.onSuccess(movieWithActors, true)
                }
            } catch (exception: Exception) {
                scope.launch(Dispatchers.Main) {
                    resultListener.onFailed(exception, true)
                }
            }
        }
    }
}