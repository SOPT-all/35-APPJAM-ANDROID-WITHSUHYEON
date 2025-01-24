package com.sopt.withsuhyeon.core.component.floatingbutton

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun AnimatedAddFindSuhyeonPostButton(
    text: String,
    lazyListState: LazyListState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isScrolled by remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset > 0 }
    }

    val floatingButtonWidth by animateDpAsState(
        targetValue = if (isScrolled) 48.dp else 95.dp,
        animationSpec = tween(durationMillis = 300), label = "Floating Button Width State"
    )

    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
            .height(48.dp)
            .width(floatingButtonWidth),
        containerColor = colors.Purple500,
        contentColor = colors.White,
        shape = RoundedCornerShape(41.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_24),
                contentDescription = stringResource(R.string.add_find_suhyeon_post_button_description),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = text,
                style = typography.body03_SB,
                maxLines = 1
            )

            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}