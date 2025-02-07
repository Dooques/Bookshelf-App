package com.example.androidcourse_18_bookshelfapp.ui

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
import com.example.androidcourse_18_bookshelfapp.model.Bookshelf
import com.example.androidcourse_18_bookshelfapp.model.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<Bookshelf.Volume>): BookshelfUiState
    data class Error(val error: HttpException?): BookshelfUiState
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
                val bookList = bookshelfRepository.searchLibrary(processSearchTerms())
                val volumeList = getVolumes(bookList)
                BookshelfUiState.Success(volumeList)
            } catch (e: IOException) {
                BookshelfUiState.Error(error = null)
            } catch (e: HttpException) {
                BookshelfUiState.Error(e)
            }
        }
    }

    private suspend fun getVolumes(bookshelf: Bookshelf.BookList): List<Bookshelf.Volume> {
        var volumeList: List<Bookshelf.Volume> = mutableListOf()
        var bookCounter = 0
        for (book in bookshelf.items) {
            if (bookCounter >= 20) {
                break
            }
            val volume =  bookshelfRepository.getVolume(book.id)
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

    private fun cleanText(volume: Bookshelf.Volume) {
        val string = volume.volumeInfo.description
        volume.volumeInfo.description = Jsoup.parse(string).text()
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