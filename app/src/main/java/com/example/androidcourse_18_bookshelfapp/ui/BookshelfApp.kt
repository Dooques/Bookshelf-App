package com.example.androidcourse_18_bookshelfapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidcourse_18_bookshelfapp.R
import com.example.androidcourse_18_bookshelfapp.ui.screens.HomeScreen
import com.example.androidcourse_18_bookshelfapp.ui.screens.ShelfScreen

enum class BookshelfRoutes(@StringRes val title: Int) {
    Home(R.string.home),
    Shelf(R.string.shelf),
}

@Composable
fun BookshelfApp(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()
    val bookshelfViewModel: BookshelfViewModel = viewModel(
        factory = BookshelfViewModel.Factory
    )
    NavHost(
        navController = navController,
        startDestination = BookshelfRoutes.Home.name,
        modifier = modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = BookshelfRoutes.Home.name) {
            val searchUiState by bookshelfViewModel.searchUiState.collectAsState()
            HomeScreen(
                setSearchTerms = { bookshelfViewModel.setSearchTerms(it) },
                onClick = {
                    if (searchUiState.search.isNotEmpty()) {
                        navController.navigate(route = BookshelfRoutes.Shelf.name)
                    }
                          },
            )
        }
        composable(route = BookshelfRoutes.Shelf.name) {
            val searchUiState by bookshelfViewModel.searchUiState.collectAsState()
            LaunchedEffect(key1 = true) {
                bookshelfViewModel.getBookData()
            }
            ShelfScreen(
                setSearchTerms = { bookshelfViewModel.setSearchTerms(it) },
                searchAgain = {
                    if (searchUiState.search.isNotEmpty()) {
                        bookshelfViewModel.getBookData()
                    }
                              },
                bookshelfViewModel = bookshelfViewModel,
            )
        }
    }
}