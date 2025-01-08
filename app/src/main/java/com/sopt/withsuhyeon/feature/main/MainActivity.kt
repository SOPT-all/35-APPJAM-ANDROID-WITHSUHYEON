package com.sopt.withsuhyeon.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sopt.withsuhyeon.feature.dummy.DummyScreen
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WithSuhyeonTheme {
                DummyScreen(userId = 2)
            }
        }
    }
}