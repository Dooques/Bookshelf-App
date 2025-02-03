package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.ui.theme.AppTypography
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme
import com.example.androidcourse_18_bookshelfapp.ui.theme.bodyFontFamily

@Composable
fun HomeScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxHeight(0.7f)
        ) {
            Title()
            Description()
            SearchBar(onClick = onClick)
        }
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.library_picture),
                contentDescription = "Man taking a book from a Library.",
                modifier = modifier.padding(
                    start = 16.dp,
                    bottom = 64.dp
                )
            )
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Box {
        Text(
            text = "The",
            fontSize = 50.sp,
            style = AppTypography.titleSmall,
            modifier = modifier.padding(start = 50.dp)
        )
        Text(
            text = "Bookshelf",
            style = AppTypography.titleMedium,
            fontSize = 80.sp,
            lineHeight = 130.sp
        )
    }
}

@Composable
fun Description(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to The Bookshelf, please use the search bar below to explore our vast library" +
            " of titles.",
        fontSize = 18.sp,
        fontFamily = bodyFontFamily,
        modifier = modifier.padding(32.dp)
    )
}

@Composable
fun SearchBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(
            modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
        )
        Spacer(modifier = modifier.size(40.dp))
        SearchButton(
            onClick = onClick,
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
    }
}
@Composable
fun SearchField(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "Beat Poetry",
        onValueChange = {},
        modifier = modifier
    )
}

@Composable
fun SearchButton(
    onClick: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier
    ) { Text("Search") }
}


@Preview
@Composable
fun HomeScreenPreview() {
    BookshelfTheme {
        Surface {
            HomeScreen(onClick = {})
        }
    }
}