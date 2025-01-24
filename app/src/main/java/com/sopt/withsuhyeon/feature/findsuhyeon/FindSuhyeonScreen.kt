package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.LocationBottomSheet
import com.sopt.withsuhyeon.core.component.dropdown.text.TextSelectDropDownWithIcon
import com.sopt.withsuhyeon.core.component.floatingbutton.AnimatedAddFindSuhyeonPostButton
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.findsuhyeon.component.DateChipListRow
import com.sopt.withsuhyeon.feature.findsuhyeon.component.FindSuhyeonPostItem
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

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
        onPostButtonClick = navigateToUpload,
        onCloseButtonClick = navigateUp,
        onPostItemClick = navigateToPost
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindSuhyeonScreen(
    padding: PaddingValues,
    onPostButtonClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    onPostItemClick: (Long) -> Unit,
    viewModel: FindSuhyeonViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val pullToRefreshState = rememberPullToRefreshState()
    val maxOffset = 60.dp
    val offset = with(LocalDensity.current) {
        if ((pullToRefreshState.distanceFraction * maxOffset.toPx()).toDp() >= maxOffset)
            maxOffset
        else
            (pullToRefreshState.distanceFraction * maxOffset.toPx()).toDp()
    }

    LaunchedEffect(Unit) {
        viewModel.getFindSuhyeonAllPost(
            state.selectedSubLocation.orEmpty(),
            state.selectedDate
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainTopNavBar(
                text = stringResource(R.string.find_suhyeon),
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
                onClick = {
                    viewModel.showLocationBottomSheet()
                }
            )
            DateChipListRow(
                dateList = state.findSuhyeonData.days,
                selectedDate = if (state.selectedDate == "") "전체" else state.selectedDate,
                onSelect = { viewModel.selectDate(it) },
                modifier = Modifier.background(colors.White).padding(start = 8.dp)
            )
            if (state.findSuhyeonData.posts.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(colors.Grey50)
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .pullToRefresh(
                            isRefreshing = state.isRefreshing,
                            state = pullToRefreshState,
                            onRefresh = {
                                viewModel.refreshFindSuhyeonAllPost(
                                    state.selectedSubLocation.orEmpty(),
                                    state.selectedDate
                                )
                            }
                        ),
                ) {
                    item {
                        Spacer(Modifier.height(offset))
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.img_empty),
                                    tint = Color.Unspecified,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(150.dp)
                                )
                                Text(
                                    text = "아직 게시글이 없어요",
                                    style = typography.body03_R.merge(colors.Grey400)
                                )
                            }
                        }
                    }
                }
            } else
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(colors.Grey50)
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .pullToRefresh(
                            isRefreshing = state.isRefreshing,
                            state = pullToRefreshState,
                            onRefresh = {
                                viewModel.refreshFindSuhyeonAllPost(
                                    state.selectedSubLocation.orEmpty(),
                                    state.selectedDate
                                )
                            }
                        ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = lazyListState
                ) {

                    item {
                        Spacer(Modifier.height(offset))
                    }
                    itemsIndexed(state.findSuhyeonData.posts) { index, post ->
                        val isLastItem = index == state.findSuhyeonData.posts.size - 1
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

        AnimatedAddFindSuhyeonPostButton(
            text = stringResource(R.string.btn_add_find_suhyeon_post),
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            lazyListState = lazyListState,
            onClick = onPostButtonClick,
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