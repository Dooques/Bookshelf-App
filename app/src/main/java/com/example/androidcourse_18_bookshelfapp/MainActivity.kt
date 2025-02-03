package com.example.androidcourse_18_bookshelfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.androidcourse_18_bookshelfapp.ui.BookshelfApp
import com.example.androidcourse_18_bookshelfapp.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             BookshelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BookshelfApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}