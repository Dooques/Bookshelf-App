package com.example.androidcourse_18_bookshelfapp.data

import androidx.compose.ui.res.stringResource
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
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
    val placeHolderBookData: Bookshelf.Volume = Bookshelf.Volume(
        id = "0",
        volumeInfo = Bookshelf.VolumeInfo(
            title = "Neuromancer",
            authors = listOf("William Gibson"),
            publisher = "Ace Books",
            publishedDate = "1984",
            description = R.string.neuromancer_description.toString(),
            industryIdentifiers = listOf(Bookshelf.Identifier("ISBN", R.string.ISBN.toString())),
            pageCount = 231,
            printType = "Book",
            categories = listOf("Science Fiction", "Cyberpunk"),
            imageLinks = Bookshelf.ImageLink(
                large =
                "https://books.google.com/books/content?id=3CjfiHmIlQQC&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE73c0A4uqYQ3GVvpyfa7NkI-wF1B6ask5ak7px6AqjXcRSnQdbT8m_8QozuImgU7ibDjHZkKtYr0BIPWTm4ACG0BFV2KKvRM8snthxweyhAgdVZA-H_dU9acjhwMtRBYfFeAp0q8&source=gbs_api"),
            language = "en"
        ),
        saleInfo = Bookshelf.SaleInfo(
            country = "GB",
            saleability = "NOT_FOR_SALE",
            isEbook = false,
        ),
    )
    val metaDataLayout: Map<String, List<Pair<String, Any>>> = mapOf(
        "about" to listOf(
            Pair("Originally published: ", placeHolderBookData.volumeInfo.publishedDate),
//            Pair("Awards:\n", placeHolderBookData.awards),
//            Pair("Nominations:\n", placeHolderBookData.nominations),
            Pair("Genres:\n", placeHolderBookData.volumeInfo.categories),
            Pair("Languages: ", placeHolderBookData.volumeInfo.language),
//            Pair("Followed by: ", placeHolderBookData.volumeInfo.),
//            Pair("Adaptations:\n", placeHolderBookData.v),
            Pair("Subject: \n", placeHolderBookData.volumeInfo.categories)
        ),
        "editionCol1" to listOf(
            Pair("ISBN: ", placeHolderBookData.volumeInfo.industryIdentifiers[0].identifier),
            Pair("Page Count: ", placeHolderBookData.volumeInfo.pageCount),
            Pair("Published: ", placeHolderBookData.volumeInfo.publishedDate),
            Pair("Format: ", placeHolderBookData.volumeInfo.printType),

        ),
        "editionCol2" to listOf(
            Pair("Publisher: ", placeHolderBookData.volumeInfo.publisher),
            Pair("Digitized: ", placeHolderBookData.volumeInfo.publishedDate),
            Pair("Language: ", placeHolderBookData.volumeInfo.language),
            Pair("Author: ", placeHolderBookData.volumeInfo.authors[0]),
        )
    )
}