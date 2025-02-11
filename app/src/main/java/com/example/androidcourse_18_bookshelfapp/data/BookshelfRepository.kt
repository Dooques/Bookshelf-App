package com.example.androidcourse_18_bookshelfapp.data

import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel
import com.example.androidcourse_18_bookshelfapp.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun searchLibrary(searchTerm: String): BookshelfModel.BookList
    suspend fun getVolume(volumeId: String): BookshelfModel.Volume
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService,
): BookshelfRepository {
    override suspend fun searchLibrary(searchTerm: String): BookshelfModel.BookList {
        return bookshelfApiService.searchLibrary(
            string = searchTerm,
        )
    }

    override suspend fun getVolume(volumeId: String): BookshelfModel.Volume {
        return bookshelfApiService.getVolume(
            string = volumeId,
        )
    }
}