package com.example.androidcourse_18_bookshelfapp

import com.example.androidcourse_18_bookshelfapp.data.NetworkBookshelfRepository
import com.example.androidcourse_18_bookshelfapp.fake.FakeBookshelfApiService
import com.example.androidcourse_18_bookshelfapp.fake.FakeDataSource
import com.example.androidcourse_18_bookshelfapp.fake.TestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BookshelfModelViewModelTest {
    @get: Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun networkBookshelfRepository_searchLibrary_verifyBookList() {
        runTest {
            val repository = NetworkBookshelfRepository(
                bookshelfApiService = FakeBookshelfApiService()
            )

            val bookList = repository.searchLibrary("final fantasy")
            assertEquals(FakeDataSource.bookList, bookList)
        }
    }

    @Test
    fun networkBookshelfRepository_getVolume_verifyVolume() {
        runTest {
            val repository = NetworkBookshelfRepository(
                bookshelfApiService = FakeBookshelfApiService()
            )
            val volume = repository.getVolume("idOne")

            println(volume.volumeInfo.description)
            assertEquals(FakeDataSource.volume, volume)
        }
    }
}