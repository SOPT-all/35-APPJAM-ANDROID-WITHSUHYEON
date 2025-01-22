package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.LocationBottomSheet
import com.sopt.withsuhyeon.core.component.dropdown.text.TextSelectDropDownWithIcon
import com.sopt.withsuhyeon.core.component.floatingbutton.AddFindSuhyeonPostButton
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.findsuhyeon.component.DateChipListRow
import com.sopt.withsuhyeon.feature.findsuhyeon.component.FindSuhyeonPostItem
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun FindSuhyeonRoute(
    padding: PaddingValues,
    navigateToUpload: () -> Unit,
    navigateUp: () -> Unit,
    navigateToPost: (Long) -> Unit,
    viewModel: FindSuhyeonViewModel = hiltViewModel()
) {
    FindSuhyeonScreen(
        padding = padding,
        onCompleteButtonClick = navigateToUpload,
        onCloseButtonClick = navigateUp,
        onPostItemClick = navigateToPost
    )
}
@Composable
fun FindSuhyeonScreen(
    padding: PaddingValues,
    onCompleteButtonClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    onPostItemClick: (Long) -> Unit,
    viewModel: FindSuhyeonViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SubTopNavBar(
                text = "",
                btnIcon = painterResource(R.drawable.ic_close),
                isTextVisible = true,
                isBtnVisible = true,
                onCloseBtnClicked = onCloseButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.White)
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Grey100
            )
            TextSelectDropDownWithIcon(
                hint = "",
                isError = false,
                value = state.selectedSubLocation,
                modifier = Modifier
                    .background(colors.White)
                    .padding(16.dp),
                onClick = { viewModel.toggleLocationBottomSheet() }
            )
            DateChipListRow(
                dateList = state.dateList,
                selectedDate = state.selectedDate,
                onSelect = { viewModel.selectDate(it) }
            )
            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(state.postList) { index, post ->
                    val isLastItem = index == state.postList.size - 1
                    FindSuhyeonPostItem(
                        postItemModel = post,
                        modifier = Modifier
                            .padding(bottom = if (isLastItem) 16.dp else 0.dp)
                            .noRippleClickable { onPostItemClick(post.postId) }
                    )
                }
            }
        }

        if (state.isLocationBottomSheetVisible) {
            LocationBottomSheet(
                isVisible = true,
                subLocationList = state.subLocationList,
                onConfirmClick = { viewModel.selectLocation(it) },
                onDismiss = { viewModel.toggleLocationBottomSheet() },
                selectedMainLocation = state.selectedMainLocation,
                mainLocationList = state.mainLocationList,
                selectedSubLocation = state.selectedSubLocation,
            )
        }

        AddFindSuhyeonPostButton(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            onClick = onCompleteButtonClick
        )
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonScreen() {
    FindSuhyeonScreen(
        padding = PaddingValues(0.dp),
        {},
        {},
        onPostItemClick = { }
    )
}