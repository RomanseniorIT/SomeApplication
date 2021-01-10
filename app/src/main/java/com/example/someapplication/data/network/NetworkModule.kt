package com.example.someapplication.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class NetworkModule() {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val contentType = "application/json; charset=utf-8".toMediaType()
    private val baseUrl = "https://api.themoviedb.org/3/"

    fun provideApiService(): MoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
        return retrofit.create(MoviesApi::class.java)
    }
}