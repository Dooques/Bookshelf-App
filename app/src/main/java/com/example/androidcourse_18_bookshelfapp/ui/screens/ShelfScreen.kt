package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBook
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme

@Composable
fun ShelfScreen(
    onClick: () -> Unit,
    searchAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        ShelfGrid(onClick = onClick)
    }
}

@Composable
fun ShelfGrid(
    books: List<PlaceholderBook> = PlaceholderDataSource().placeholderBooks,
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
            key = {book -> book.title}
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
    book: PlaceholderBook,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(book.imgSrc),
        contentDescription = stringResource(book.title),
        contentScale = Fit,
        modifier = modifier.clickable(role = Role.Button, onClick = onClick)
    )
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