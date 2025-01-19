package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GenderSelectRoute(
    navigateToNext: () -> Unit,
    padding: PaddingValues,
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
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    var genderState by remember { mutableStateOf(EMPTY_STRING) }
    val backgroundMale = if (genderState == MALE) {
        Modifier
            .background(
                color = colors.Purple50,
                shape = RoundedCornerShape(size = 24.dp)
            )
            .border(
                width = 1.dp,
                color = colors.Purple100,
                shape = RoundedCornerShape(size = 24.dp)
            )
    } else {
        Modifier.background(
            color = colors.Grey25,
            shape = RoundedCornerShape(size = 24.dp)
        )
    }
    val backgroundFemale = if (genderState == FEMALE) {
        Modifier
            .background(
                color = colors.Purple50,
                shape = RoundedCornerShape(size = 24.dp)
            )
            .border(
                width = 1.dp,
                color = colors.Purple100,
                shape = RoundedCornerShape(size = 24.dp)
            )
    } else {
        Modifier.background(
            color = colors.Grey25,
            shape = RoundedCornerShape(size = 24.dp)
        )
    }

    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxSize()
    ) {
        MainTopNavBar(text = EMPTY_STRING)
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedProgressBar(
                progress = 0.66f,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OnBoardingTitle(text = stringResource(R.string.onboarding_gender_select_title))
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(156.dp)
                        .then(backgroundMale)
                        .padding(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 16.dp)
                        .noRippleClickable {
                            genderState = MALE
                            viewModel.updateGender(MALE)
                        },
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.img_boy_suma),
                        tint = Color.Unspecified,
                        contentDescription = MALE
                    )
                    Text(
                        text = MALE,
                        style = typography.title03_SB,
                        color = if (genderState == MALE) colors.Purple600 else colors.Grey400
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(156.dp)
                        .then(backgroundFemale)
                        .padding(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 16.dp)
                        .noRippleClickable {
                            genderState = FEMALE
                            viewModel.updateGender(FEMALE)
                        },
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.img_girl_suma),
                        tint = Color.Unspecified,
                        contentDescription = FEMALE
                    )
                    Text(
                        text = FEMALE,
                        style = typography.title03_SB,
                        color = if (genderState == FEMALE) colors.Purple600 else colors.Grey400
                    )
                }

            }
        }

        HorizontalDivider(modifier = Modifier.height(1.dp))
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
            isDisabled = genderState == EMPTY_STRING
        )
    }
}