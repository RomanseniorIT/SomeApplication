package com.example.someapplication.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseUseCase<T, P>(protected val resultListener: ResultListener<T>) {

    open val dispatcher: CoroutineDispatcher = Dispatchers.Default

    abstract suspend fun execute(scope: CoroutineScope, param: P)

    interface ResultListener<T> {
        fun onSuccess(result: T)

        fun onFailed(exception: Exception)
    }
}