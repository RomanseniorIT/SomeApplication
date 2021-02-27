package com.example.someapplication.usecases

import com.example.someapplication.data.MoviesRepository
import com.example.someapplication.data.model.MovieWithActors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetMovieUseCase(
    listener: BaseUseCase.ResultListener<MovieWithActors>,
    private val repository: MoviesRepository
) : BaseUseCase<MovieWithActors, Int>(listener) {

    override suspend fun execute(scope: CoroutineScope, param: Int) {
        scope.launch(dispatcher) {
            try {
                val movieFull = repository.getMovie(param)
                val actors = repository.getActors(param)
                val movieWithActors = MovieWithActors(movieFull, actors)
                scope.launch(Dispatchers.Main) {
                    resultListener.onSuccess(movieWithActors)
                }
            } catch (exception: Exception) {
                scope.launch(Dispatchers.Main) {
                    resultListener.onFailed(exception)
                }
            }
        }
    }
}