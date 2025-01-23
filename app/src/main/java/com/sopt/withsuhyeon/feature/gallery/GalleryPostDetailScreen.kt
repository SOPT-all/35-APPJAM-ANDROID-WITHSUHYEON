package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.DeletePostBottomSheet
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.profile.PostProfileInfoRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.image.downloadImage
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryPostDetailRoute(
    padding: PaddingValues,
    popBackStackToGallery: () -> Unit,
    galleryId: Long,
    viewModel: GalleryPostDetailViewModel = hiltViewModel()
) {
    GalleryPostDetailScreen(
        padding = padding,
        galleryId = galleryId,
        onDownloadBtnClick = {
            popBackStackToGallery()
        },
        onModalDeleteBtnClick = {
            popBackStackToGallery()
        },
        viewModel = viewModel
    )
}

@Composable
fun GalleryPostDetailScreen(
    padding: PaddingValues,
    onDownloadBtnClick: () -> Unit,
    onModalDeleteBtnClick: () -> Unit,
    galleryId: Long,
    viewModel: GalleryPostDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(galleryId) {
        viewModel.getGalleryPostDetail(galleryId)
    }
    val context = LocalContext.current

    var isDeleteBottomSheetVisible by remember { mutableStateOf(false) }
    var isDeleteAlertModalVisible by remember { mutableStateOf (false) }

    val galleryPostDetail by viewModel.galleryPostDetail.collectAsState()

    if (isDeleteBottomSheetVisible) {
        DeletePostBottomSheet(
            isBottomSheetVisible = isDeleteBottomSheetVisible,
            onDeleteClick = {
                isDeleteBottomSheetVisible = false
                isDeleteAlertModalVisible = true
            },
            onCloseClick = {
                isDeleteBottomSheetVisible = false
            },
            onDismiss = {
                isDeleteBottomSheetVisible = false
            }
        )
    }

    if (isDeleteAlertModalVisible) {
        AlertModal(
            onDeleteClick = {
                isDeleteAlertModalVisible = false
                viewModel.deleteGalleryPost(galleryId)
                onModalDeleteBtnClick()
            },
            onCancelClick = {
                isDeleteAlertModalVisible = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
    ) {
        SubTopNavBar(
            text = stringResource(R.string.blank),
            btnIcon = painterResource(R.drawable.ic_menu),
            isTextVisible = true,
            isBtnVisible = true,
            onCloseBtnClicked = {
                isDeleteBottomSheetVisible = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 52.dp)
                .verticalScroll(rememberScrollState())
                .background(colors.White)
        ) {
            AsyncImage(
                model = galleryPostDetail.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(colors.Grey50)
            )

            Spacer(modifier = Modifier.height(16.dp))

            MediumChip(
                mediumChipType = MediumChipType.CATEGORY,
                dynamicString = galleryPostDetail.category,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = galleryPostDetail.title,
                style = typography.body01_SB,
                color = colors.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            PostProfileInfoRow(
                profileImage = galleryPostDetail.profileImage,
                userName = galleryPostDetail.nickname,
                date = galleryPostDetail.createdAt,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Grey100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = galleryPostDetail.content,
                style = typography.body03_R,
                color = colors.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(88.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            LargeButton(
                text = stringResource(R.string.gallery_download_btn),
                onClick = {
                    val fileName = "image_${System.currentTimeMillis()}.jpg"
                    downloadImage(
                        context = context,
                        imageUrl = galleryPostDetail.imageUrl,
                        fileName = fileName
                    ) {
                        onDownloadBtnClick()
                    }
                },
                isDownloadBtn = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}