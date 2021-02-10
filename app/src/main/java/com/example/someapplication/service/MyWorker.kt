package com.example.someapplication.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.someapplication.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val repository = MoviesRepository()
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val result = withContext(Dispatchers.IO) { repository.loadMovies(1) }
            if (result.isNullOrEmpty()) {
                Result.retry()
            } else {
                Result.success()
            }
        } catch (error: Throwable) {
            val data = Data.Builder()
                .putString(ERROR_MSG, error.message)
                .build()
            Result.failure(data)
        }
    }

    companion object {
        private const val ERROR_MSG = "error_msg"
    }
}