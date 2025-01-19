package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun GenderSelectRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit
) {
    GenderSelectScreen(
        onButtonClick = navigateToNext,
        padding = padding
    )
}

@Composable
fun GenderSelectScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var genderState by remember { mutableStateOf(EMPTY_STRING) }
    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .fillMaxSize()
    ) {
        MainTopNavBar(
            text = EMPTY_STRING,
            modifier = Modifier.padding(20.dp)
        )
        AnimatedProgressBar(progress = 0.66f)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OnBoardingTitle(text = stringResource(R.string.onboarding_gender_select_title))
        Spacer(
            modifier = Modifier.height(100.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(color = colors.Grey25, shape = RoundedCornerShape(size = 24.dp))
                    .padding(
                        vertical = 16.dp,
                        horizontal = 8.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(color = colors.Grey25, shape = RoundedCornerShape(size = 24.dp))
                    .padding(
                        vertical = 16.dp,
                        horizontal = 8.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            }

        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            isDisabled = genderState == EMPTY_STRING
        )
    }
}