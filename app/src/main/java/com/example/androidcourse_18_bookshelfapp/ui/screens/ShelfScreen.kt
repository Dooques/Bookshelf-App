package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource
import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBook
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfUiState
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfViewModel
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme

@Composable
fun ShelfScreen(
    searchAgain: () -> Unit,
    setSearchTerms: (String) -> Unit,
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    var searchValue by remember { mutableStateOf("") }
    val bookUiState: BookshelfUiState = bookshelfViewModel.bookUiState
    Column {
        Row {
            TextField(
                value = searchValue,
                placeholder = { Text("Search by genre or title...") },
                onValueChange = {
                    searchValue = it
                    setSearchTerms(searchValue)
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Text",
                        modifier = modifier.clickable { searchValue = "" }
                    )
                               },
                maxLines = 1,
                modifier = modifier
                    .fillMaxWidth(0.7f)
            )
            Button(
                onClick = {
                    searchAgain()
                },
                shape = RectangleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .size(56.dp)
            ) { Text("Search") }
        }
        when (bookUiState) {
            is BookshelfUiState.Loading -> BookshelfResultsLoading()
            is BookshelfUiState.Success -> ShelfGrid(books = bookUiState.books)
            is BookshelfUiState.Error -> BookshelfResultsError(bookUiState.error)
        }
    }
}

@Composable
fun ShelfGrid(
    books: List<BookshelfModel.Volume>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(
            items = books,
            key = {book -> book.id }
        ) { book ->
            BookCover(
                book = book,
            )
        }
    }
}

@Composable
fun BookCover(
    book: BookshelfModel.Volume,
    modifier: Modifier = Modifier
) {
    val large = book.volumeInfo.imageLinks.large
    val medium = book.volumeInfo.imageLinks.medium
    val small = book.volumeInfo.imageLinks.small

    var showCard by remember { mutableStateOf(false) }
    fun flipCard() { showCard = !showCard }

    Box {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(large.let { medium.let { small } })
                .build(),
            contentDescription = book.volumeInfo.title,
            contentScale = Fit,
            modifier = modifier
                .clickable(onClick = { flipCard() })
                .aspectRatio(6f / 9f)
        )
        if (showCard) {
            BookInfoCard(
                volume = book,
                onClick = { flipCard() }
            )
        }
    }
}

@Composable
fun BookshelfResultsError(error: Exception?) {
    Surface(Modifier.fillMaxSize()) {
        Column {
            Text(error.toString())
        }
    }
}

@Composable
fun BookshelfResultsLoading() {
    Surface(Modifier.fillMaxSize()) {
        Column {
            Text("Loading...")
        }
    }
}

@Composable
fun BookInfoCard(
    volume: BookshelfModel.Volume,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fontSize = 11.sp
    val lineHeight = 12.sp
    val uriHandler = LocalUriHandler.current

    Card(
        shape = RectangleShape,
        colors = CardColors(
            containerColor = Color.Black.copy(alpha = 0.6f),
            contentColor = Color.White,
            disabledContainerColor = Color.Black.copy(alpha = 0.6f),
            disabledContentColor = Color.White,
        ),
        onClick = onClick,
        modifier = modifier
            .aspectRatio(6f / 9f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedCard(
                colors = CardColors(
                    containerColor = Color.Black.copy(alpha = 0.5f),
                    contentColor = Color.White,
                    disabledContainerColor = colorScheme.secondaryContainer,
                    disabledContentColor = colorScheme.onSecondary,
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title and Author
                if (volume.volumeInfo.title.isNotEmpty() && volume.volumeInfo.authors.isNotEmpty()) {
                    Column(modifier = modifier.padding(8.dp)) {
                        Text(volume.volumeInfo.title)
                        Text("by ${volume.volumeInfo.authors[0]}")
                    }
                }
                HorizontalDivider()

                // About Book
                Column(modifier = modifier.padding(8.dp)) {
                    if (volume.volumeInfo.publishedDate.isNotEmpty()) Text(
                        text = "Published: " + volume.volumeInfo.publishedDate,
                        fontSize = fontSize,
                        lineHeight = lineHeight,
                        modifier = modifier.padding(vertical = 8.dp)
                    )
                    if (volume.volumeInfo.categories.isNotEmpty()) {
                        var categories = ""
                        for (item in volume.volumeInfo.categories) {
                            categories += "\n" + item
                        }
                        Text(
                        text = "Categories: $categories",
                        fontSize = fontSize,
                        lineHeight = lineHeight
                        )
                    }
                    val language = if (volume.volumeInfo.language == "en")
                        "English" else "Not English"
                    if (volume.volumeInfo.language.isNotEmpty()) Text(
                        text = language,
                        fontSize = fontSize,
                        lineHeight = lineHeight,
                        modifier = modifier.padding(vertical = 8.dp)
                    )
                }
                if (volume.volumeInfo.description.isNotEmpty()) {
                    HorizontalDivider()

                    // Book Description
                    Column(modifier = modifier.padding(8.dp)) {
                        Text(
                            volume.volumeInfo.description,
                            fontSize = fontSize,
                            lineHeight = lineHeight
                        )
                    }
                }
                if (
                    volume.saleInfo.retailPrice?.amount?.isNaN() == false
                    && !volume.saleInfo.buyLink.isNullOrBlank()
                    ) {
                    HorizontalDivider()
                    Column(
                        modifier
                            .padding(8.dp)
                            .clickable {
                                val link = volume.saleInfo.buyLink
                                uriHandler.openUri(link)
                            }

                    ) {
                        Text("Get the book")
                        AffiliateLink(
                            volume = volume,
                            fontSize = fontSize,
                            lineHeight = lineHeight,
                            modifier = modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AffiliateLink(
    volume: BookshelfModel.Volume,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
            .padding(
                vertical = 4.dp
            )
    ) {
        Icon(
            Icons.Default.ShoppingCart,
            contentDescription = "",
            Modifier.size(25.dp)
        )
        Spacer(modifier.size(8.dp))
        Column {
            Text("Google Play Store", fontSize = fontSize, lineHeight = lineHeight)
            Text(
                "£${volume.saleInfo.retailPrice?.amount} - eBook",
                fontSize = fontSize,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
fun ShelfGridPreview(
    books: List<PlaceholderBook>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(
            items = books,
            key = {book -> book.title}
        ) { book ->
            BookCoverPreview(
                book = book,
            )
        }
    }
}

@Composable
fun BookCoverPreview(
    book: PlaceholderBook,
    modifier: Modifier = Modifier
) {
    var showCard by remember { mutableStateOf(true) }
    fun onClick() { showCard = !showCard }
    Image(
        painter = painterResource(book.imgSrc),
        contentDescription = stringResource(book.title),
        contentScale = Fit,
        modifier = modifier
            .clickable(onClick = { onClick() })
            .aspectRatio(6f / 9f)
    )
    if (showCard) {
       BookInfoCard(
           volume = BookshelfModel.Volume(),
           onClick = { onClick() }
       )
    }
}

@Preview
@Composable
fun ShelfScreenPreview() {
    BookshelfTheme {
        Surface {
            Column {
                Row(Modifier.fillMaxHeight(0.08f)) {
                    var searchValue by remember { mutableStateOf("") }
                    TextField(
                        value = searchValue,
                        placeholder = { Text("Search") },
                        onValueChange = {
                            searchValue = it
                        },
                        maxLines = 1,
                    )
                    Button(
                        onClick = {},
                        shape = RectangleShape,
                    ) { Text("Search") }
                }
                ShelfGridPreview(
                    books = PlaceholderDataSource().placeholderBooks,
                    modifier = Modifier,
                    contentPadding = PaddingValues(0.dp),
                )
            }
        }
    }
}