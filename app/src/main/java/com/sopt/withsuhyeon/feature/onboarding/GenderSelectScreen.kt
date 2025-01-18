package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle

@Composable
fun GenderSelectRoute(
    navigateToNext : () -> Unit
) {

}

@Composable
fun GenderSelectScreen(
    onButtonClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    var genderState by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        AnimatedProgressBar(progress = 0.5f)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OnBoardingTitle(text = stringResource(R.string.onboarding_nickname_screen_title))
        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
        )
    }
}