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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.DeletePostBottomSheet
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.profile.PostProfileInfoRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryPostDetailRoute(
    padding: PaddingValues,
    popBackStackToGallery: () -> Unit
) {
    GalleryPostDetailScreen(
        padding = padding,
        onDownloadBtnClick = {
            popBackStackToGallery()
        }
    )
}

@Composable
fun GalleryPostDetailScreen(
    padding: PaddingValues,
    onDownloadBtnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val category by remember { mutableStateOf("") }
    val galleryPostTitle by remember { mutableStateOf("") }
    val profileImage by remember { mutableStateOf("") }
    val userName by remember { mutableStateOf("") }
    val date by remember { mutableStateOf("") }
    val galleryPostContent by remember { mutableStateOf("") }

    var isDeleteBottomSheetVisible by remember { mutableStateOf(false) }
    var isDeleteAlertModalVisible by remember { mutableStateOf (false) }

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
                onDownloadBtnClick()
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(colors.Grey500)
            )

            Spacer(modifier = Modifier.height(16.dp))

            MediumChip(
                mediumChipType = MediumChipType.CATEGORY,
                dynamicString = category,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = galleryPostTitle,
                style = typography.body01_SB,
                color = colors.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            PostProfileInfoRow(
                profileImage = profileImage,
                userName = userName,
                date = date,
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
                text = galleryPostContent,
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
                onClick = onDownloadBtnClick,
                isDownloadBtn = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}