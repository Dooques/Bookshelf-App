package com.example.androidcourse_18_bookshelfapp.data

import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.example.androidcourse_18_bookshelfapp.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun searchLibrary(searchTerm: String): Bookshelf.BookList
    suspend fun getVolume(volumeId: String): Bookshelf.Volume
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService,
): BookshelfRepository {
    override suspend fun searchLibrary(searchTerm: String): Bookshelf.BookList {
        return bookshelfApiService.getBookData(searchTerm)
    }

    override suspend fun getVolume(volumeId: String): Bookshelf.Volume {
        return bookshelfApiService.getVolume(volumeId)
    }
}