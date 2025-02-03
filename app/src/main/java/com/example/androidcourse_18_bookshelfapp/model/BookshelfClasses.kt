package com.example.androidcourse_18_bookshelfapp.model


class Bookshelf {
    data class BookList(
        val kind: String
    )

    data class Book(
        val kind: String,
        val id: String,
        val etag: String,
        val selfLink: String,
        val volumeInfo: BookInfo,
        val imageLinks: BookImages
    )

    data class BookInfo(
        val title: String,
        val authors: List<String>
    )
    data class BookImages(
        val large: String
    )
}