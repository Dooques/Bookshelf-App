package com.example.androidcourse_18_bookshelfapp.network

import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun getBookData(
        @Query("q") string: String,
        @Query("maxResults") maxResults: String = "40",
        @Query("key") apiKey: String = "AIzaSyAFN5Sj-uX7Ux6Aeunromdk-oaPGpRV47o"
    ): Bookshelf.BookList

    @GET("books/v1/volumes/{volumeId}")
    suspend fun getVolume(
        @Path("volumeId") string: String,
        @Query("key") apiKey: String = "AIzaSyAFN5Sj-uX7Ux6Aeunromdk-oaPGpRV47o"
    ): Bookshelf.Volume
}