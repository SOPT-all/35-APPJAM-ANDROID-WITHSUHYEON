package com.sopt.withsuhyeon.feature.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.sopt.withsuhyeon.domain.repository.UserRepository
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        enableEdgeToEdge()
        lifecycleScope.launch {
            userRepository.connectWithUserId().onSuccess {
                Log.e("성공", "dd")
            }.onFailure {
                Log.e("실패", "ㅇㅇㅇ")
            }
            Log.e("main", "sdf")
        }
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            WithSuhyeonTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}