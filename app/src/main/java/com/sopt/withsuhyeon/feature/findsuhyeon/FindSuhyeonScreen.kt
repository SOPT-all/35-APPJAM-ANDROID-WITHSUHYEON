package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.core.component.floatingbutton.AddFindSuhyeonPostButton

@Composable
fun FindSuhyeonRoute(
    padding: PaddingValues,
    viewModel: FindSuhyeonViewModel = hiltViewModel()
) {
    FindSuhyeonScreen(
        padding = padding
    )
}
@Composable
private fun FindSuhyeonScreen(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Find Suhyeon Screen"
            )
        }

        AddFindSuhyeonPostButton(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            onClick = { }
        )
    }
}