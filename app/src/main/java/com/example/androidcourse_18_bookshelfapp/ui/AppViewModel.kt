package com.example.androidcourse_18_bookshelfapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidcourse_18_bookshelfapp.BookshelfApplication
import com.example.androidcourse_18_bookshelfapp.data.BookshelfRepository
import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel
import com.example.androidcourse_18_bookshelfapp.model.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.jsoup.Jsoup
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<BookshelfModel.Volume>): BookshelfUiState
    data class Error(val error: Exception?): BookshelfUiState
    data object Loading: BookshelfUiState
}

class BookshelfViewModel(
    private val bookshelfRepository: BookshelfRepository
): ViewModel() {
    var bookUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    private var _searchUiState =
        MutableStateFlow(SearchUiState(""))
    val searchUiState:
            StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    init {
        getBookData()
    }

    fun setSearchTerms(search: String) {
        _searchUiState.update { currentState ->
            currentState.copy(
                search = search
            )
        }
    }

    private fun processSearchTerms(): String =
        searchUiState.value.search.replace(" ", "+").lowercase()

    fun getBookData() {
        viewModelScope.launch {
            bookUiState = BookshelfUiState.Loading
            bookUiState = try {
                val bookList = bookshelfRepository.searchLibrary(
                    processSearchTerms()
                )
                val volumeList = getVolumes(bookList)
                BookshelfUiState.Success(volumeList)
            } catch (e: IOException) {
                Log.e("BookshelfViewModel.searchLibrary", "Network error: ${e.message}")
                BookshelfUiState.Error(e)
            } catch (e: HttpException) {
                Log.e("BookshelfViewModel.searchLibrary", "Http error: ${e.message}")
                BookshelfUiState.Error(e)
            } catch (e: Exception) {
                Log.e("BookshelfViewModel.searchLibrary", "Unexpected error: ${e.message}")
                val responseBody = "Unexpected Error".toResponseBody("text/plain".toMediaTypeOrNull())
                val response = Response.error<Any>(500, responseBody)
                BookshelfUiState.Error(HttpException(response))
            }
        }
    }

    private suspend fun getVolumes(bookshelf: BookshelfModel.BookList): List<BookshelfModel.Volume> {
        var volumeList: List<BookshelfModel.Volume> = mutableListOf()
        var bookCounter = 0
        for (book in bookshelf.items) {
            if (bookCounter >= 20) {
                break
            }
            var volume = BookshelfModel.Volume()
            try {
                volume = bookshelfRepository.getVolume(book.id)
            } catch (e: IOException) {
                Log.e("BookshelfViewModel.getVolume", "Network error: ${e.message}")
                bookUiState = BookshelfUiState.Error(e)
            } catch (e: HttpException) {
                Log.e("BookshelfViewModel.getVolume", "Http error: ${e.message}")
                bookUiState = BookshelfUiState.Error(e)
            } catch (e: Exception) {
                Log.e("BookshelfViewModel.getVolume", "Unexpected error: ${e.message}")
                val responseBody = "Unexpected Error".toResponseBody("text/plain".toMediaTypeOrNull())
                val response = Response.error<Any>(500, responseBody)
                bookUiState = BookshelfUiState.Error(HttpException(response))
            } finally {
               if (volume == BookshelfModel.Volume()) {
                   break
               }
            }
            if (volume.volumeInfo.imageLinks.large.isEmpty()
                || volume.volumeInfo.imageLinks.medium.isEmpty()
                || volume.volumeInfo.imageLinks.small.isEmpty()
                ) {
                continue
            }
            updateUrl(volume)
            cleanText(volume)
            volumeList = volumeList + volume
            bookCounter++
        }
        return volumeList
    }

    private fun cleanText(volume: BookshelfModel.Volume) {
        val string = volume.volumeInfo.description
        volume.volumeInfo.description = Jsoup.parse(string).text()
    }

    private fun updateUrl(volume: BookshelfModel.Volume) {
        val large = volume.volumeInfo.imageLinks.large
        volume.volumeInfo.imageLinks.large =large.replace("http", "https")

        val medium = volume.volumeInfo.imageLinks.medium
        volume.volumeInfo.imageLinks.medium = medium.replace("http", "https")

        val small = volume.volumeInfo.imageLinks.small
        volume.volumeInfo.imageLinks.small = small.replace("http", "https")

        val thumbnail = volume.volumeInfo.imageLinks.thumbnail
        volume.volumeInfo.imageLinks.thumbnail = thumbnail.replace("http", "https")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository)
            }
        }
    }
}