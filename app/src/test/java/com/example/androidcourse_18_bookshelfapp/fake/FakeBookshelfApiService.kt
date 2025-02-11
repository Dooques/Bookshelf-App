package com.example.androidcourse_18_bookshelfapp.fake

import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel
import com.example.androidcourse_18_bookshelfapp.network.BookshelfApiService

class FakeBookshelfApiService: BookshelfApiService {
    override suspend fun searchLibrary(
        string: String, maxResults: String, apiKey: String
    ): BookshelfModel.BookList {
        return FakeDataSource.bookList
    }

    override suspend fun getVolume(
        string: String, apiKey: String
    ): BookshelfModel.Volume {
        return FakeDataSource.volume
    }

}