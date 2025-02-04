package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidcourse_18_bookshelfapp.model.Book
import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfUiState
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfViewModel
import retrofit2.HttpException

@Composable
fun BookTempScreen(

) {
    val bookshelfViewModel: BookshelfViewModel = viewModel()
    when (val bookshelfUiState = bookshelfViewModel.bookshelfUiState) {
        is BookshelfUiState.Loading -> TempBookshelfResultsLoading()
        is BookshelfUiState.Success -> BookshelfResultsSuccess(
            bookshelfUiState.books,
        )
        is BookshelfUiState.Error -> TempBookshelfResultsError(bookshelfUiState.error)
    }
}

@Composable
fun BookshelfResultsSuccess(
    bookshelf: List<Bookshelf.Volume>,
) {
    Surface(Modifier.fillMaxSize()) {
        Column {
            Text(bookshelf.size.toString())
            LazyColumn {
                items(items = bookshelf, key = { item -> item.id }) { volume ->
                    val small = volume.volumeInfo.imageLinks.small
                    val thumbnail = volume.volumeInfo.imageLinks.thumbnail
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(small.ifEmpty { thumbnail })
                            .build(),
                        contentDescription = volume.volumeInfo.title,
                        modifier = Modifier.size(size = 200.dp)
                    )
                    Column(Modifier.padding(9.dp)) {
                        Text(volume.volumeInfo.title)
                        Text(
                            "small = " + volume.volumeInfo.imageLinks.small.isNotEmpty().toString() + "\n"
                            + volume.volumeInfo.imageLinks.small + "\n"
                        )
                        Text(
                            "medium = " + volume.volumeInfo.imageLinks.medium.isNotEmpty().toString() + "\n" +
                                    volume.volumeInfo.imageLinks.medium + "\n"
                        )
                        Text("large = " + volume.volumeInfo.imageLinks.large.isNotEmpty().toString() + "\n" +
                            volume.volumeInfo.imageLinks.large + "\n"
                        )
                        Text("thumbnail = " + volume.volumeInfo.imageLinks.thumbnail.isNotEmpty().toString() + "\n" +
                                volume.volumeInfo.imageLinks.thumbnail + "\n")
                    }
                }
            }
        }
    }
}

@Composable
fun TempBookshelfResultsError(error: HttpException?) {
    Surface(Modifier.fillMaxSize()) {
        Text(error.toString())
    }
}

@Composable
fun TempBookshelfResultsLoading() {
    Surface(Modifier.fillMaxSize()) {
        Text("Loading")
    }
}

