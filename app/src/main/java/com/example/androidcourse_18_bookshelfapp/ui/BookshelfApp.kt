package com.example.androidcourse_18_bookshelfapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.data.PlaceholderDataSource
import com.example.androidcourse_18_bookshelfapp.ui.screens.BookScreen
import com.example.androidcourse_18_bookshelfapp.ui.screens.BookTempScreen
import com.example.androidcourse_18_bookshelfapp.ui.screens.HomeScreen
import com.example.androidcourse_18_bookshelfapp.ui.screens.ShelfScreen

enum class BookshelfRoutes(@StringRes val title: Int) {
    Home(R.string.home),
    Shelf(R.string.shelf),
    Book(R.string.book)
}


@Composable
fun BookshelfApp(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BookshelfRoutes.Home.name,
        modifier = modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = BookshelfRoutes.Home.name) {
            HomeScreen(
                onClick = { navController.navigate(route = BookshelfRoutes.Shelf.name) },
            )
        }
        composable(route = BookshelfRoutes.Shelf.name) {
            ShelfScreen(
                onClick = { navController.navigate(route = BookshelfRoutes.Book.name)},
                searchAgain = {}
            )
        }
        composable(route = BookshelfRoutes.Book.name) {
//            BookTempScreen()
            BookScreen(
                book = PlaceholderDataSource().placeHolderBookData
            )
        }
    }
}