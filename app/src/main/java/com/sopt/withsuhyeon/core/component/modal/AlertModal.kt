package com.sopt.withsuhyeon.core.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.SmallButton
import com.sopt.withsuhyeon.core.util.KeyStorage.ALERT_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.DISABLED_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.EXIT_ALERT_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.LEAVE_ALERT_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.LOGOUT_ALERT_TYPE
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun AlertModal(
    onDeleteClick: () -> Unit,
    onCancelClick: () -> Unit,
    alertModalType: String = DEFAULT,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onCancelClick
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = colors.White,
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                when (alertModalType) {
                    LOGOUT_ALERT_TYPE -> {
                        Text(
                            text = stringResource(R.string.alert_modal_logout_title),
                            style = typography.title03_B,
                            color = colors.Black
                        )
                    }
                    EXIT_ALERT_TYPE -> {
                        Text(
                            text = stringResource(R.string.alert_modal_exit),
                            style = typography.title03_B,
                            color = colors.Black
                        )
                    }
                    LEAVE_ALERT_TYPE -> {
                        Text(
                            text = stringResource(R.string.alert_modal_leave_title),
                            style = typography.title03_B,
                            color = colors.Black
                        )
                    }

                    else -> {
                        Text(
                            text = stringResource(R.string.alert_modal_delete_post_question),
                            style = typography.title03_B,
                            color = colors.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                when (alertModalType) {
                    LEAVE_ALERT_TYPE, EXIT_ALERT_TYPE -> {
                        Text(
                            text = stringResource(R.string.alert_modal_leave_sub_title),
                            style = typography.body03_SB,
                            color = colors.Grey500
                        )
                    }

                    DEFAULT -> {
                        Text(
                            text = stringResource(R.string.alert_modal_delete_post_description),
                            style = typography.body03_SB,
                            color = colors.Grey500
                        )
                    }

                    else -> {}
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (alertModalType) {
                        EXIT_ALERT_TYPE ->
                            SmallButton(
                                text = stringResource(R.string.alert_modal_continue_btn),
                                type = DISABLED_TYPE,
                                modifier = Modifier.weight(1f)
                            ) {
                                onCancelClick()
                            }
                        else ->
                            SmallButton(
                                text = stringResource(R.string.alert_modal_cancel_btn),
                                type = DISABLED_TYPE,
                                modifier = Modifier.weight(1f)
                            ) {
                                onCancelClick()
                            }

                    }
                    when (alertModalType) {
                        LOGOUT_ALERT_TYPE -> {
                            SmallButton(
                                text = stringResource(R.string.alert_modal_logout),
                                type = DEFAULT,
                                modifier = Modifier.weight(1f)
                            ) {
                                onDeleteClick()
                            }
                        }

                        LEAVE_ALERT_TYPE -> {
                            SmallButton(
                                text = stringResource(R.string.alert_modal_leave),
                                type = ALERT_TYPE,
                                modifier = Modifier.weight(1f)
                            ) {
                                onDeleteClick()
                            }
                        }
                        EXIT_ALERT_TYPE ->
                            SmallButton(
                                text = stringResource(R.string.alert_modal_exit),
                                type = ALERT_TYPE,
                                modifier = Modifier.weight(1f)
                            ) {
                                onDeleteClick()
                            }
                        else -> {
                            SmallButton(
                                text = stringResource(R.string.alert_modal_delete_btn),
                                type = ALERT_TYPE,
                                modifier = Modifier.weight(1f)
                            ) {
                                onDeleteClick()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlertModalPreview() {
    WithSuhyeonTheme {
        AlertModal({}, {})
    }
}