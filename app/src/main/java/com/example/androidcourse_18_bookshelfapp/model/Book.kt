package com.example.androidcourse_18_bookshelfapp.model

import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource

data class Book(
    val title: Int,
    val imgSrc: Int,
    val authorship: Int,
    val publishDate: Int,
    val publisher: Int,
    val awards: Int,
    val genres: Int,
    val rating: Int
)

data class PlaceholderBook(
    val title: Int,
    val imgSrc: Int,
)

data class PlaceholderBookData(
    val title: Int,
    val imgSrc: Int,
    val description: Int,
    val authorship: Int,
    val publishDate: Int,
    val publisher: Int,
    val awards: Int,
    val nominations: Int,
    val genres: Int,
    val subject: Int,
    val rating: Int,
    val originalLanguage: Int,
    val sequel: Int,
    val adaptations: Int,
    val isbn: Int,
    val pageCount: Int,
    val editionPublished: Int,
    val format: Int,
    val digitized: Int,
    val contributor: Int,
)

data class MetaDataAsMap(
    val book: PlaceholderBookData,
    val metaData: Map<String, Any> = mapOf(
        "aboutWork" to mapOf(
            "published" to Pair(
                book.publishDate,
                PlaceholderDataSource().metaDataLayout[""]
                ),
            "awards" to book.awards,
            "nominations" to book.nominations,
            "genres" to book.genres,
            "originalLanguage" to book.originalLanguage,
            "sequel" to book.sequel,
            "adaptations" to book.adaptations,
            "subject" to book.subject,
        ),
        "aboutEditionCol1" to mapOf(
            "ISBN" to book.isbn,
            "pageCount" to book.pageCount,
            "published" to book.editionPublished,
            "format" to book.format,
        ),
        "aboutEditionCol2" to listOf(
            "publisher" to book.publisher,
            "digitized" to book.digitized,
            "language" to book.originalLanguage,
            "contributor" to book.contributor,
        ),
        "description" to book.description
    )
)
