package com.example.someapplication.data.network

import com.example.someapplication.BuildConfig
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

    fun provideApiService(): MoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE))
            .build()
        return retrofit.create(MoviesApi::class.java)
    }

    companion object {
        private val CONTENT_TYPE = "application/json; charset=utf-8".toMediaType()
    }
}