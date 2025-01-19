package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.button.ShowButton
import com.sopt.withsuhyeon.core.component.checkbox.CheckBox
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.CHECKED
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.SECONDARY_TYPE
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun TermsOfUseRoute(
    navigateToNext: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    TermsOfUseScreen(
        onButtonClick = {
            navigateToNext()
        }
    )
}

@Composable
fun TermsOfUseScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    var isAllTermsSelected by remember { mutableStateOf(false) }
    var isAgedSelected by remember { mutableStateOf(false) }
    var isTermsSelected by remember { mutableStateOf(false) }
    var isPersonalInformationSelected by remember { mutableStateOf(false) }

    fun updateAllTermsSelectedState() {
        isAllTermsSelected = isAgedSelected && isTermsSelected && isPersonalInformationSelected
    }
    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(
                vertical = 16.dp,
            )
            .fillMaxSize()
    ) {
        MainTopNavBar(
            text = EMPTY_STRING,
        )
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        AnimatedProgressBar(
            progress = 0.1f,
            modifier = Modifier.padding(
                16.dp
            )
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OnBoardingTitle(stringResource(R.string.onboarding_temrs_of_use_title))
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CheckBox(
                placeholder = stringResource(R.string.onboarding_temrs_of_use_all_agree),
                onClick = {
                    isAllTermsSelected = !isAllTermsSelected
                    if (isAllTermsSelected) {
                        isAgedSelected = true
                        isTermsSelected = true
                        isPersonalInformationSelected = true
                    } else {
                        isAgedSelected = false
                        isTermsSelected = false
                        isPersonalInformationSelected = false
                    }
                },
                state = if (isAllTermsSelected) CHECKED else DEFAULT
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = colors.Grey100, RoundedCornerShape(size = 24.dp)
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    ),

                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                ) {
                    CheckBox(
                        placeholder = stringResource(R.string.onboarding_terms_of_use_age),
                        onClick = {
                            isAgedSelected = !isAgedSelected
                            updateAllTermsSelectedState()
                        },
                        type = SECONDARY_TYPE,
                        state = if (isAgedSelected) CHECKED else DEFAULT
                    )
                    ShowButton(onClick = {
                        // TODO - 정책 연결
                    })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                ) {
                    CheckBox(
                        placeholder = stringResource(R.string.onboarding_terms_of_use_agree),
                        onClick = {
                            isTermsSelected = !isTermsSelected
                            updateAllTermsSelectedState()
                        },
                        type = SECONDARY_TYPE,
                        state = if (isTermsSelected) CHECKED else DEFAULT
                    )
                    ShowButton(onClick = {
                        // TODO - 정책 연결
                    })
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                ) {
                    CheckBox(
                        placeholder = stringResource(R.string.onboarding_temrs_of_use_personal_information),
                        onClick = {
                            isPersonalInformationSelected = !isPersonalInformationSelected
                            updateAllTermsSelectedState()

                        },
                        type = SECONDARY_TYPE,
                        state = if (isPersonalInformationSelected) CHECKED else DEFAULT
                    )
                    ShowButton(onClick = {
                        // TODO - 정책 연결
                    })
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )

        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                if (isAllTermsSelected) {
                    onButtonClick()
                }
            },
            text = NEXT_BUTTON_TEXT,
            isDisabled = !isAllTermsSelected,
        )
    }
}