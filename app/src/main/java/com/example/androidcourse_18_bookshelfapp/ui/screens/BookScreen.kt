package com.example.androidcourse_18_bookshelfapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource
import com.example.androidcourse_18_bookshelfapp.model.PlaceholderBookData
import com.example.androidcourse_18_bookshelfapp.ui.theme.AppTypography
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme


@Composable
fun BookScreen(
    book: PlaceholderBookData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        BookCard(book = book,)
        BookMoreData(book = book)
        AffiliatesCard()
    }
}

@Composable
fun BookCard(
    book: PlaceholderBookData,
    modifier: Modifier = Modifier
) {
    val lineHeight = 12.sp
    val fontSize = 10.sp
    val padding = 4.dp
    val coverSize = painterResource(R.drawable.neuromancer).intrinsicSize

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(coverSize.height)
            .padding(8.dp)
    ) {

        // Cover of book
        Image(
            painter =painterResource(book.imgSrc),
            contentDescription = stringResource(book.title),
            modifier = modifier
                .fillMaxWidth(0.5f)
        )

        // Title and author of book
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(start = 4.dp)
        ) {
            Text(
                text = stringResource(book.title),
                style = AppTypography.titleLarge,
                fontSize = 36.sp,
                modifier = modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "by ${stringResource(book.authorship)}",
                style = AppTypography.titleMedium,
                fontSize = 18.sp,
                modifier = modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 0.dp,
                    bottom = 8.dp
                )
            )

            // General Information about book
            AboutCard(
                fontSize = fontSize,
                lineHeight = lineHeight,
                padding = padding,
                modifier = modifier
            )
        }
    }
}

@Composable
fun BookMoreData(
    book: PlaceholderBookData,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val lineHeight = 12.sp
        val fontSize = 9.sp

        Column {
            // Split columns showing book metadata
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Metadata",
                    style = AppTypography.titleSmall
                )
            }
            MetaDataCols(
                lineHeight = lineHeight,
                fontSize = fontSize,
            )
            HorizontalDivider()
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Description",
                    style = AppTypography.titleSmall
                )
            }

            // Description of book
            Text(
                text = stringResource(book.description),
                fontSize = fontSize,
                lineHeight = lineHeight,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
@Composable
fun AffiliatesCard(
    modifier: Modifier = Modifier
) {
    val lineHeight = 12.sp
    val fontSize = 9.sp

    Card(
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            // Affiliate Links
            Text(
                text = "Get the book",
                style = AppTypography.titleSmall,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
        Column(
            modifier = modifier.padding(vertical = 4.dp)
        ) {
            listOf(1, 2, 3).forEach { _ ->
                AffiliateLink(fontSize, lineHeight)
            }
        }
    }
}


@Composable
fun AffiliateLink(fontSize: TextUnit, lineHeight: TextUnit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(
                horizontal = 32.dp,
                vertical = 4.dp
            )
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.amazon_shopping_alt_svgrepo_com),
                contentDescription = "",
                Modifier.size(25.dp)
            )
            Spacer(Modifier.size(16.dp))
            Column {
                Text("Amazon.co.uk", fontSize = fontSize, lineHeight = lineHeight)
                Text("£9.99 - Hardcover Book", fontSize = fontSize, lineHeight = lineHeight)
            }
        }
        Text("Get Book", fontSize = fontSize, lineHeight = lineHeight)
    }
}

@Composable
fun AboutCard(
    fontSize: TextUnit,
    lineHeight: TextUnit,
    padding: Dp,
    modifier: Modifier = Modifier
) {
    val aboutData = PlaceholderDataSource().metaDataLayout["about"]
    Card(
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight(),

            ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "About the work",
                    style = AppTypography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.padding(
                    start = 8.dp,
                    top = 4.dp,
                    bottom = 4.dp
                )
            ) {
                aboutData?.forEach { item ->
                    Text(
                        text =  item.first + stringResource(item.second),
                        fontSize = fontSize,
                        lineHeight = lineHeight,
                        modifier = modifier.padding(vertical = padding)
                    )
                }
            }
        }
    }
}

@Composable
fun MetaDataCols(
    fontSize: TextUnit,
    lineHeight: TextUnit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = 16.dp,
                end = 32.dp
            )
    ) {
        Column {
            PlaceholderDataSource().metaDataLayout["editionCol1"]?.forEach { item ->
                Text(
                    text = item.first + stringResource(item.second),
                    fontSize = fontSize,
                    lineHeight = lineHeight,
                    modifier = modifier.padding()
                )
            }
        }
        Column {
            PlaceholderDataSource().metaDataLayout["editionCol2"]?.forEach { item ->
                Text(
                    text = item.first + stringResource(item.second),
                    fontSize = fontSize,
                    lineHeight = lineHeight,
                    modifier = modifier.padding()
                )
            }
        }
    }
}


@Preview
@Composable
fun BookScreenPreview() {
    BookshelfTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BookScreen(PlaceholderDataSource().placeHolderBookData)
        }
    }
}