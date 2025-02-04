package com.example.androidcourse_18_bookshelfapp.network

import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val baseUrl = "https://www.googleapis.com/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(baseUrl)
    .build()

interface BooksApiService {
    @GET("books/v1/volumes")
    suspend fun searchLibrary(
        @Query("q") string: String,
        @Query("maxResults") maxResults: Int = 40
    ): Bookshelf.BookList

    @GET("books/v1/volumes/{volumeId}")
    suspend fun getVolume(
        @Path("volumeId") string: String
    ): Bookshelf.Volume
}

object BookshelfApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}