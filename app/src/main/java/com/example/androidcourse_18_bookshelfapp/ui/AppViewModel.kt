package com.example.androidcourse_18_bookshelfapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.example.androidcourse_18_bookshelfapp.network.BookshelfApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<Bookshelf.Volume>): BookshelfUiState
    data class Error(val error: HttpException?): BookshelfUiState
    data object Loading: BookshelfUiState
}

class BookshelfViewModel: ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
    private var _bookUiState = MutableStateFlow(listOf(Bookshelf.Volume()))
    val bookUiState: StateFlow<List<Bookshelf.Volume>> = _bookUiState.asStateFlow()

    init {
        getBookData()

    }

    private fun getBookData() {
        viewModelScope.launch {
            bookshelfUiState = BookshelfUiState.Loading
            bookshelfUiState = try {
                val bookList = BookshelfApi.retrofitService.searchLibrary("final+fantasy")
                val volumeList = getVolumes(bookList)
                BookshelfUiState.Success(volumeList)
            } catch (e: IOException) {
                BookshelfUiState.Error(error = null)
            } catch (e: HttpException) {
                BookshelfUiState.Error(e)
            }
        }
    }

    fun returnVolumeData() = bookUiState.value

    private suspend fun getVolumes(bookshelf: Bookshelf.BookList): List<Bookshelf.Volume> {
        var volumeList: List<Bookshelf.Volume> = mutableListOf()
        var bookCounter = 0
        for (book in bookshelf.items) {
            if (bookCounter >= 20) {
                break
            }
            val volume =  BookshelfApi.retrofitService.getVolume(book.id)
            if (volume.volumeInfo.imageLinks.large.isEmpty()
                || volume.volumeInfo.imageLinks.medium.isEmpty()
                || volume.volumeInfo.imageLinks.small.isEmpty()
                ) {
                continue
            }
            updateUrl(volume)
            volumeList = volumeList + volume
            bookCounter++
        }
        return volumeList
    }

    private fun updateUrl(volume: Bookshelf.Volume) {
        val large = volume.volumeInfo.imageLinks.large
        volume.volumeInfo.imageLinks.large =large.replace("http", "https")

        val medium = volume.volumeInfo.imageLinks.medium
        volume.volumeInfo.imageLinks.medium = medium.replace("http", "https")

        val small = volume.volumeInfo.imageLinks.small
        volume.volumeInfo.imageLinks.small = small.replace("http", "https")

        val thumbnail = volume.volumeInfo.imageLinks.thumbnail
        volume.volumeInfo.imageLinks.thumbnail = thumbnail.replace("http", "https")
    }
}