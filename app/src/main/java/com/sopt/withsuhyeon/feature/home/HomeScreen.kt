package com.sopt.withsuhyeon.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.BlockBottomSheet
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.feature.home.component.HomeCardItem
import com.sopt.withsuhyeon.feature.home.component.HomePostItem
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToGallery: () -> Unit,
    navigateToGalleryWithCategory: (String) -> Unit,
    navigateToPost: (Long) -> Unit,
    navigateToBlockUser: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        padding = padding,
        onCategoryCardClick = navigateToGalleryWithCategory,
        onViewAllButtonClick = navigateToGallery,
        onPostClick = navigateToPost,
        navigateToBlockUser = navigateToBlockUser,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onPostClick: (Long) -> Unit,
    onCategoryCardClick: (String) -> Unit,
    onViewAllButtonClick: () -> Unit,
    navigateToBlockUser: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getHomeData()
        viewModel.startCountAnimation()
        viewModel.getIsFirstLogin()
    }
    val homeState by viewModel.state.collectAsStateWithLifecycle()
    var isBlockBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(homeState.isFirstLogin) {
        isBlockBottomSheetVisible = homeState.isFirstLogin
    }

    if (isBlockBottomSheetVisible) {
        BlockBottomSheet(
            closeSheet = {
                isBlockBottomSheetVisible = false
                viewModel.setIsFirstLogin()
                         },
            navigateToBlockScreen = navigateToBlockUser,
            nickname = homeState.homeData.nickname
        )
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            homeState.lottieResId
        )
    )

    val pullToRefreshState = rememberPullToRefreshState()

    val maxOffset = 60.dp
    val offset = with(LocalDensity.current) {
        if ((pullToRefreshState.distanceFraction * maxOffset.toPx()).toDp() >= maxOffset)
            maxOffset
        else
            (pullToRefreshState.distanceFraction * maxOffset.toPx()).toDp()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4703B5),
                        Color(0xFF207AFC),
                        Color.White
                    )
                )
            )
            .statusBarsPadding()


    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo),
            tint = Color.Unspecified,
            modifier = Modifier.padding(top = 10.dp, start = 16.dp, bottom = 18.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullToRefresh(
                    isRefreshing = homeState.isRefreshing,
                    state = pullToRefreshState,
                    onRefresh = {
                        viewModel.refreshHomeData()
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
            ) {
                Spacer(Modifier.height(offset))
                Box {
                    LottieAnimation(
                        modifier = Modifier
                            .width(180.dp)
                            .height(200.dp)
                            .align(Alignment.BottomEnd),
                        composition = composition,
                        iterations = Int.MAX_VALUE,
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 20.dp)
                            .background(color = Color.Transparent)
                    ) {
                        Spacer(modifier = Modifier.height(61.dp))
                        Text(
                            stringResource(R.string.home_title_count),
                            style = typography.body03_SB.merge(colors.Grey25),
                        )
                        Row(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                        ) {
                            Text(
                                homeState.count.toDecimalFormat(),
                                style = typography.heading01_B.copy(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight(800),
                                    lineHeight = 34.sp,
                                ).merge(
                                    colors.White
                                ),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                stringResource(R.string.home_count),
                                style = typography.heading01_SB.merge(colors.White),
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .background(color = colors.White)
                        .padding(top = 17.dp, bottom = 24.dp)
                        .fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 7.dp, bottom = 7.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.home_title_now),
                            style = typography.title03_B.merge(colors.Black)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 13.dp)
                                .noRippleClickable(onViewAllButtonClick)
                        ) {
                            Text(
                                text = stringResource(R.string.home_view_all),
                                style = typography.caption01_SB.merge(colors.Grey800)
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_right_arrow),
                                tint = colors.Grey800,
                                modifier = Modifier.size(16.dp),
                                contentDescription = stringResource(R.string.home_view_all)
                            )
                        }
                    }
                    Spacer(Modifier.height(9.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Spacer(Modifier.width(8.dp))
                        }
                        item {
                            HomeCardItem(
                                "쉿! 여행 중엔\n" +
                                        "방해 금지 모드",
                                imageResource = R.drawable.img_do_not_disturb,
                                textColor = colors.Black,
                                modifier = Modifier.noRippleClickable {
                                    onCategoryCardClick("공항")
                                }
                            )
                        }
                        item {
                            HomeCardItem(
                                "꼭꼭 숨어라\n" +
                                        "나 오늘 집에 안간다",
                                imageResource = R.drawable.img_seek_and_hide,
                                textColor = colors.White,
                                modifier = Modifier.noRippleClickable {
                                    onCategoryCardClick("캠핑/글램핑")
                                }
                            )
                        }
                        item {
                            HomeCardItem(
                                "쉿! 여행 중엔\n" +
                                        "방해 금지 모드",
                                imageResource = R.drawable.img_study_cafe,
                                textColor = colors.White,
                                modifier = Modifier.noRippleClickable {
                                    onCategoryCardClick("스터디카페")
                                }
                            )
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colors.Grey50
                )
                Box(
                    modifier = Modifier
                        .background(color = colors.White)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.img_megaphone),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .alpha(0.3f)
                            .align(Alignment.TopEnd)
                    )
                    Column(
                        modifier = Modifier.height(542.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.home_text_around),
                                style = typography.title03_B.merge(colors.Purple500)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = stringResource(R.string.home_title_post),
                                style = typography.title03_B.merge(colors.Black)
                            )
                        }
                        Spacer(modifier = Modifier.height(17.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_location_home),
                                tint = colors.Grey400,
                                modifier = Modifier.size(16.dp),
                                contentDescription = stringResource(R.string.home_view_all)
                            )
                            Spacer(Modifier.width(3.dp))
                            Text(
                                text = homeState.homeData.region,
                                style = typography.body03_SB.merge(colors.Grey400)
                            )
                        }
                        if (homeState.homeData.homePosts.isEmpty()) {
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(vertical = 16.dp)
                                    .height(426.dp)
                                    .background(colors.White, RoundedCornerShape(24.dp))
                                    .border(
                                        1.dp,
                                        colors.Grey100,
                                        RoundedCornerShape(24.dp)
                                    ),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Spacer(Modifier.height(118.dp))
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.img_empty),
                                        tint = Color.Unspecified,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(150.dp)
                                    )
                                    Text(
                                        text = "아직 게시글이 없어요",
                                        style = typography.body03_R.merge(colors.Grey400)
                                    )
                                    Spacer(Modifier.height(118.dp))
                                }
                            }

                        } else {
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(vertical = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                homeState.homeData.homePosts.forEachIndexed { index, post ->
                                    val isLastItem = index == homeState.homeData.homePosts.size - 1
                                    HomePostItem(
                                        postItemModel = post,
                                        modifier = Modifier
                                            .padding(bottom = if (isLastItem) 32.dp else 0.dp)
                                            .noRippleClickable {
                                                onPostClick(post.postId)
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen(
        padding = PaddingValues(0.dp),
        onPostClick = { },
        onCategoryCardClick = { },
        onViewAllButtonClick = { },
        navigateToBlockUser = { },
    )
}
