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
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun AlertModal(
    onDelete: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onCancel
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = colors.White,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.alert_modal_delete_post_question),
                    style = typography.title03_B,
                    color = colors.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.alert_modal_delete_post_description),
                    style = typography.body03_SB,
                    color = colors.Grey500
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SmallButton(
                        text = stringResource(R.string.alert_modal_cancel_btn),
                        type = "disabled",
                        modifier = Modifier.weight(1f)
                    ) {
                        onCancel()
                    }

                    SmallButton(
                        text = stringResource(R.string.alert_modal_delete_btn),
                        type = "alert",
                        modifier = Modifier.weight(1f)
                    ) {
                        onDelete()
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun AlertModalPreview() {
    WithSuhyeonTheme {
        AlertModal({}, {})
    }
}