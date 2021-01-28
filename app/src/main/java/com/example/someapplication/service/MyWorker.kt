package com.example.someapplication.service

import android.content.Context
import androidx.work.CoroutineWorker
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
            val result = withContext(Dispatchers.IO) { repository.loadMovies() }
            if (result.isNullOrEmpty()) {
                Result.retry()
            } else {
                Result.success()
            }
        } catch (error: Throwable) {
            Result.failure()
        }
    }
}