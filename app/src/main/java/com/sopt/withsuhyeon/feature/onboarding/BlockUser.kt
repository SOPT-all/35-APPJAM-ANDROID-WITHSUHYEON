package com.sopt.withsuhyeon.feature.onboarding

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.listitem.BlockPhoneNumberItem
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.SAVE_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.regex.checkValidPhoneNumber
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.BlockUserViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BlockUserRoute(
    padding: PaddingValues,
    navigateToPreviousScreen: () -> Unit,
    viewModel: BlockUserViewModel = hiltViewModel()
) {
    BlockUserScreen(
        padding = padding,
        onSaveButtonClick = navigateToPreviousScreen,
        viewModel = viewModel
    )
}

@Composable
fun BlockUserScreen(
    padding: PaddingValues,
    onSaveButtonClick: () -> Unit,
    viewModel: BlockUserViewModel,
    modifier: Modifier = Modifier,
) {
    var isValid by remember { mutableStateOf(false) }
    val state by viewModel.blockUserState.collectAsStateWithLifecycle()
    val dividerBorderColor = colors.Grey100

    LaunchedEffect(Unit) {
        viewModel.getBlockUser()
    }

    Column(
        modifier = modifier
            .background(colors.White)
            .padding(padding)
            .fillMaxSize()
            .imePadding()
    ) {
        SubTopNavBar(
            text = stringResource(R.string.block_screen_title),
            btnIcon = painterResource(R.drawable.ic_close),
            isTextVisible = true,
            isBtnVisible = true,
            onCloseBtnClicked = onSaveButtonClick,
            modifier = Modifier.drawBehind {
                val borderThickness = 1.dp.toPx()
                drawLine(
                    color = dividerBorderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderThickness
                )
            },
        )
        HorizontalDivider(color = colors.Grey100)
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.block_screen_sub_title),
                style = typography.title02_B
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "차단한 사용자는 ${state.nickname}님의 게시글을 볼 수 없어요",
                //  TODO - 닉네임 연동
                style = typography.caption01_SB.merge(colors.Grey400)
            )
        }
        HorizontalDivider(modifier = Modifier.height(4.dp), color = colors.Grey100)
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.block_screen_phone_number),
                style = typography.body03_SB.merge(colors.Grey600)
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicShortTextField(
                value = state.blockNumber,
                onValueChange = { input ->
                    if (!isValid) {
                        viewModel.refreshErrorMessage()
                    }
                    if (input.length <= 11) {
                        viewModel.selectBlockUserNumber(input)
                        isValid = input.length == 11 && input.checkValidPhoneNumber()
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                hint = stringResource(R.string.block_screen_phone_number_hint),
                maxLength = 11,
                errorMessage = state.errorMessage,
                trailingContent = {
                    BasicButtonForTextField(
                        text = stringResource(R.string.block_screen_block_text),
                        onClick = {
                            if (isValid) {
                                viewModel.postBlockUser(
                                    state.blockNumber,
                                    onError = { isValid = false }
                                )
                                viewModel.selectBlockUserNumber(EMPTY_STRING)
                                isValid = false
                            }
                        },
                        modifier = Modifier,
                        enabled = isValid,
                    )
                }
            )
        }
        HorizontalDivider(modifier = Modifier.height(4.dp), color = colors.Grey100)

        if (state.blockNumbers.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.img_empty),
                    contentDescription = stringResource(R.string.block_screen_empty_image)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.block_screen_empty_text),
                    style = typography.body03_R.merge(colors.Grey400)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.block_screen_block_number_text),
                        style = typography.caption01_SB.merge(color = colors.Grey500)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = state.blockNumbers.size.toString(),
                        style = typography.caption01_SB
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(state.blockNumbers) { phoneNumber ->
                        BlockPhoneNumberItem(
                            phoneNumber = phoneNumber,
                            onDeleteClick = { viewModel.deleteBlockUser(phoneNumber) }
                        )
                    }
                }
            }
        }
        HorizontalDivider(color = colors.Grey100)
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = onSaveButtonClick,
            text = SAVE_BUTTON_TEXT,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            isDisabled = false
        )
    }
}


