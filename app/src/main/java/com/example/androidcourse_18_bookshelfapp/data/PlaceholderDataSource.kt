package com.example.androidcourse_18_bookshelfapp.data

import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBook
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBookData

class PlaceholderDataSource {
    val placeholderBooks: List<PlaceholderBook> = listOf(
        PlaceholderBook(R.string.war_of_the_worlds, R.drawable.war_of_the_worlds),
        PlaceholderBook(R.string.neuromancer, R.drawable.neuromancer),
        PlaceholderBook(R.string.soft_machine, R.drawable.soft_machine),
        PlaceholderBook(R.string.cat_s_cradle, R.drawable.cats_cradle),
        PlaceholderBook(R.string.dharma_bums, R.drawable.dharma_bums),
        PlaceholderBook(R.string.fear_and_loathing, R.drawable.fear_and_loathing)
    )
    val placeHolderBookData: PlaceholderBookData = PlaceholderBookData(
        title = R.string.neuromancer_title,
        imgSrc = R.drawable.neuromancer, // Placeholder - will be converted to drawable resource
        description = R.string.neuromancer_description,
        authorship = R.string.neuromancer_authorship, // Placeholder - will be converted to stringResource
        publishDate = R.string.neuromancer_publish_date, // Placeholder - will be converted to stringResource
        publisher = R.string.neuromancer_publisher, // Placeholder - will be converted to stringResource
        awards = R.string.neuromancer_awards, // Placeholder - will be converted to stringResource
        genres = R.string.neuromancer_category, // Placeholder - will be converted to stringResource
        nominations = R.string.neuromancer_category, // Placeholder - will be converted to stringResource
        rating = R.string.neuromancer_rating,
        originalLanguage = R.string.english,
        sequel = R.string.count_zero,
        adaptations = R.string.neuromancer,
        subject = R.string.neuromancer_category,
        isbn = R.string.ISBN,
        pageCount = R.string.page_count,
        editionPublished = R.string.neuromancer_publish_date,
        format = R.string.format,
        contributor = R.string.contributor,
        digitized = R.string.digitized
    )
    val metaDataLayout: Map<String, List<Pair<String, Int>>> = mapOf(
        "about" to listOf(
            Pair("Originally published: ", placeHolderBookData.publishDate),
            Pair("Awards:\n", placeHolderBookData.awards),
            Pair("Nominations:\n", placeHolderBookData.nominations),
            Pair("Genres:\n", placeHolderBookData.genres),
            Pair("Languages: ", placeHolderBookData.originalLanguage),
            Pair("Followed by: ", placeHolderBookData.sequel),
            Pair("Adaptations:\n", placeHolderBookData.adaptations),
            Pair("Subject: \n", placeHolderBookData.subject)
        ),
        "editionCol1" to listOf(
            Pair("ISBN: ", placeHolderBookData.isbn),
            Pair("Page Count: ", placeHolderBookData.pageCount),
            Pair("Published: ", placeHolderBookData.editionPublished),
            Pair("Format: ", placeHolderBookData.format),

        ),
        "editionCol2" to listOf(
            Pair("Publisher: ", placeHolderBookData.publisher),
            Pair("Digitized: ", placeHolderBookData.digitized),
            Pair("Language: ", placeHolderBookData.originalLanguage),
            Pair("Author: ", placeHolderBookData.authorship),
        )

    )
}