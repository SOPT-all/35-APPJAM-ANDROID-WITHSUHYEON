package com.sopt.withsuhyeon.feature.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.type.ProfileType
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun SelectProfileRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit
) {
    SelectProfileScreen(
        padding = padding,
        onButtonClick = navigateToNext
    )
}

@SuppressLint("ResourceType")
@Composable
fun SelectProfileScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profileTypes = ProfileType.entries
    var profileImage by remember { mutableIntStateOf(R.drawable.img_grey_suma) }
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
            OnBoardingTitle(text = stringResource(R.string.onboarding_profile_select_title))
            Spacer(modifier = Modifier.height(48.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(60.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .width(170.dp)
                        .height(170.dp),
                    imageVector = ImageVector.vectorResource(profileImage),
                    contentDescription = stringResource(R.string.profile_image),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 17.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    profileTypes.forEach { profileType ->
                        Image(
                            modifier = Modifier
                                .width(74.dp)
                                .height(74.dp)
                                .alpha(if (profileImage == profileType.titleResId) 1f else 0.3f)
                                .noRippleClickable {
                                    profileImage = profileType.titleResId
                                },
                            imageVector = ImageVector.vectorResource((profileType.titleResId)),
                            contentDescription = stringResource(R.string.profile_image),
                        )
                    }
                }
            }
        }
        HorizontalDivider(modifier = Modifier.height(1.dp))
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
            isDisabled = profileImage == R.drawable.img_grey_suma
        )
    }
}