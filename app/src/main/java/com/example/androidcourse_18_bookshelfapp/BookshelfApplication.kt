package com.example.androidcourse_18_bookshelfapp

import android.app.Application
import com.example.androidcourse_18_bookshelfapp.network.AppContainer
import com.example.androidcourse_18_bookshelfapp.network.DefaultAppContainer

class BookshelfApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}