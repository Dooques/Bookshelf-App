package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource
import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBook
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfUiState
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfViewModel
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme
import retrofit2.HttpException

@Composable
fun ShelfScreen(
    onClick: () -> Unit,
    searchAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bookshelfViewModel: BookshelfViewModel = viewModel()
    Column {
        Row(Modifier.fillMaxHeight(0.08f)) {
            SearchField(
                modifier = modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight()
            )
            SearchButton(
                onClick = searchAgain,
                shape = RectangleShape,
                modifier = modifier
                    .fillMaxSize()
            )
        }
        when (val bookshelfUiState = bookshelfViewModel.bookshelfUiState) {
            is BookshelfUiState.Loading -> BookshelfResultsLoading()
            is BookshelfUiState.Success -> ShelfGrid(bookshelfUiState.books, onClick)
            is BookshelfUiState.Error -> BookshelfResultsError(bookshelfUiState.error)
        }
    }
}

@Composable
fun ShelfGrid(
    books: List<Bookshelf.Volume>,
    onClick: () -> Unit,
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
            key = {book -> book.volumeInfo.title}
        ) { book ->
            BookCover(
                book = book,
                onClick = onClick
            )
        }
    }
}

@Composable
fun BookCover(
    book: Bookshelf.Volume,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val large = book.volumeInfo.imageLinks.large
    val medium = book.volumeInfo.imageLinks.medium
    val small = book.volumeInfo.imageLinks.small

    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(large.ifEmpty { medium.ifEmpty { small } })
            .build(),
        contentDescription = book.volumeInfo.title,
        contentScale = Fit,
        modifier = modifier
            .clickable(onClick = onClick)
            .aspectRatio(6f/9f)
    )
}

@Composable
fun BookshelfResultsError(error: HttpException?) {
    Surface(Modifier.fillMaxSize()) {
        Text(error.toString())
    }
}

@Composable
fun BookshelfResultsLoading() {
    Surface(Modifier.fillMaxSize()) {
        Text("Loading")
    }
}

@Preview
@Composable
fun ShelfScreenPreview() {
    BookshelfTheme {
        Surface {
            ShelfScreen(
                onClick = {},
                searchAgain = {}
            )
        }
    }
}