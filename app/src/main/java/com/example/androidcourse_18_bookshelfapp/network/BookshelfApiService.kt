package com.example.androidcourse_18_bookshelfapp.network

import com.example.androidcourse_18_bookshelfapp.BuildConfig
import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun searchLibrary(
        @Query("q") string: String,
        @Query("maxResults") maxResults: String = "30",
        @Query("key") apiKey: String = BuildConfig.G_BOOKS_API_KEY
    ): BookshelfModel.BookList

    @GET("books/v1/volumes/{volumeId}")
    suspend fun getVolume(
        @Path("volumeId") string: String,
        @Query("key") apiKey: String = BuildConfig.G_BOOKS_API_KEY
    ): BookshelfModel.Volume
}