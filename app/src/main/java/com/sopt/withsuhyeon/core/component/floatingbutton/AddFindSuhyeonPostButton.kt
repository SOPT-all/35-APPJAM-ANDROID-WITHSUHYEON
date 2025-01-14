package com.sopt.withsuhyeon.core.component.floatingbutton

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun AddFindSuhyeonPostButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(56.dp),
        containerColor = colors.Purple500,
        contentColor = colors.White,
        shape = RoundedCornerShape(28.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus_24),
            contentDescription = stringResource(R.string.add_find_suhyeon_post_button_description),
            modifier = Modifier.size(24.dp)
        )
    }
}