package com.example.androidcourse_18_bookshelfapp.ui.screens

import android.inputmethodservice.Keyboard
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.model.SearchUiState
import com.example.androidcourse_18_bookshelfapp.ui.theme.AppTypography
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme
import com.example.androidcourse_18_bookshelfapp.ui.theme.bodyFontFamily
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    setSearchTerms: (String) -> Unit,
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
            SearchBar(
                onClick = onClick,
                setSearchTerms = setSearchTerms
            )
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
    setSearchTerms: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var searchValue by remember { mutableStateOf("") }
        TextField(
            value = searchValue,
            placeholder = { Text("Search by genre or title...") },
            onValueChange = {
                searchValue = it
                setSearchTerms(searchValue)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onClick() }
            ),
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth(0.7f)
        )
        Spacer(modifier = modifier.size(40.dp))
        Button(
            onClick = {
                    onClick()
                      },
            shape = RectangleShape,
            modifier = modifier
                .fillMaxWidth(0.7f)
        ) { Text("Search") }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    BookshelfTheme {
        Surface {
            HomeScreen(
                onClick = {},
                setSearchTerms = {}
            )
        }
    }
}