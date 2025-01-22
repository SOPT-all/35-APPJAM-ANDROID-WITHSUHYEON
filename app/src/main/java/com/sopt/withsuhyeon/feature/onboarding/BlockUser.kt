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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.listitem.BlockPhoneNumberItem
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.SAVE_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BlockUserRoute(
    padding: PaddingValues,
    navigateToPreviousScreen: () -> Unit
) {
    BlockUserScreen(
        padding = padding,
        onSaveButtonClick =  navigateToPreviousScreen
    )
}

@Composable
fun BlockUserScreen(
    padding: PaddingValues,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(EMPTY_STRING) }
    var isValid by remember { mutableStateOf(false) }

    var phoneNumberList by remember { mutableStateOf(listOf<String>()) }
    val isEmpty by remember { derivedStateOf { phoneNumberList.isEmpty() } }

    Column(
        modifier = modifier
            .background(colors.White)
            .padding(padding)
            .fillMaxSize()
    ) {
        SubTopNavBar(
            text = stringResource(R.string.block_screen_title),
            btnIcon = painterResource(R.drawable.ic_xclose_24),
            onCloseBtnClicked = onSaveButtonClick
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
                text = stringResource(R.string.block_screen_description),
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
                value = value,
                onValueChange = { input ->
                    isValid = input.length == 11
                    value = input
                },
                hint = stringResource(R.string.block_screen_phone_number_hint),
                trailingContent = {
                    BasicButtonForTextField(
                        text = stringResource(R.string.block_screen_block_text),
                        onClick = {
                            if (isValid) {
                                phoneNumberList = listOf(value) + phoneNumberList
                                value = EMPTY_STRING
                            }
                        },
                        modifier = Modifier,
                        enabled = true
                    )
                }
            )
        }
        HorizontalDivider(modifier = Modifier.height(4.dp), color = colors.Grey100)

        if (isEmpty) {
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
                        text = phoneNumberList.size.toString(),
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
                    items(phoneNumberList) { phoneNumber ->
                        BlockPhoneNumberItem(
                            modifier = Modifier.noRippleClickable {
                                phoneNumberList = phoneNumberList - phoneNumber
                            },
                            phoneNumber = phoneNumber,
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
            modifier = Modifier.padding(horizontal = 16.dp),
            isDisabled = phoneNumberList.isEmpty()
        )
    }
}
