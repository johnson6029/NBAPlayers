package com.example.nbaplayers.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class ApiClient {
    private val baseUrl = "https://raw.githubusercontent.com/scoremedia/"
    private val contentType: MediaType = MediaType.get("application/json")

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl).
            addConverterFactory(Json.asConverterFactory(contentType)).
            build()
    }
}
