package com.sopt.withsuhyeon.feature.gallery

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.GalleryCategoryBottomSheet
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDown
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.textfield.LongTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.image.createImagePart
import com.sopt.withsuhyeon.data.dto.request.RequestUploadGalleryDto
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryUploadRoute(
    padding: PaddingValues,
    popBackStackToGallery: () -> Unit
) {
    GalleryUploadScreen(
        padding = padding,
        onCompleteBtnClick = {
            popBackStackToGallery()
        },
        onCloseBtnClick = {
            popBackStackToGallery()
        }
    )
}

@Composable
private fun GalleryUploadScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    onCompleteBtnClick: () -> Unit,
    onCloseBtnClick: () -> Unit,
    galleryViewModel: GalleryViewModel = hiltViewModel(),
    galleryUploadViewModel: GalleryUploadViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    var titleValue by remember { mutableStateOf("") }
    var isTitleValid by remember { mutableStateOf(true) }
    val titleErrorMessage = if (!isTitleValid) "필수로 입력해줘" else ""

    var selectedCategory by remember { mutableStateOf("") }
    var hoveredCategory by remember { mutableStateOf("") }
    var isCategoryValid by remember { mutableStateOf(true) }
    val categoryErrorMessage = if (!isCategoryValid) "필수로 입력해줘" else ""

    var galleryUploadDescription by remember { mutableStateOf("") }
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var isCompleteBtnEnabled by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
        ) {
            SubTopNavBar(
                text = stringResource(R.string.gallery_sub_nav_bar_title),
                btnIcon = painterResource(R.drawable.ic_close),
                isTextVisible = true,
                isBtnVisible = true,
                onCloseBtnClicked = {
                    onCloseBtnClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .background(colors.White)
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val categories by galleryViewModel.categories.collectAsState()

            if (isBottomSheetVisible) {
                GalleryCategoryBottomSheet(
                    isVisible = isBottomSheetVisible,
                    categories = categories,
                    selectedCategory = hoveredCategory,
                    onCategoryChipClick = { category ->
                        hoveredCategory = category
                    },
                    onConfirmClick = {
                        selectedCategory = hoveredCategory
                        isCategoryValid = selectedCategory.isNotEmpty()
                        isBottomSheetVisible = false
                    },
                    onDismiss = {
                        isBottomSheetVisible = false
                    }
                )
            }

            GalleryUploadWithPicker(
                onImageSelected = { uri ->
                    selectedImageUri = uri
                }
            )

            HorizontalDivider(
                thickness = 4.dp,
                color = colors.Grey50
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = stringResource(R.string.category_subtitle),
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            TextDropDown(
                value = selectedCategory,
                hint = stringResource(R.string.category_text_drop_down_hint),
                isError = !isCategoryValid,
                errorMessage = categoryErrorMessage,
                onClick = {
                    isBottomSheetVisible = true
                },
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = stringResource(R.string.post_title_subtitle),
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BasicShortTextField(
                    value = titleValue,
                    onValueChange = { newValue ->
                        titleValue = newValue
                        isTitleValid = newValue.isNotEmpty()
                    },
                    hint = "텍스트를 입력해주세요",
                    isValid = isTitleValid,
                    errorMessage = titleErrorMessage,
                    visibleLength = true,
                    maxLength = 30,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = "설명 (선택)",
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            LongTextField(
                value = galleryUploadDescription,
                hint = stringResource(R.string.post_title_description_hint),
                onValueChange = { newDescription ->
                    galleryUploadDescription = newDescription
                },
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = modifier.height(80.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
                .align(BottomCenter)
        ) {
            LargeButton(
                text = "완료",
                onClick = {
                    if (isCompleteBtnEnabled) {
                        val isTitleInputValid = titleValue.isNotEmpty()
                        val isCategoryInputValid = selectedCategory.isNotEmpty()

                        isTitleValid = isTitleInputValid
                        isCategoryValid = isCategoryInputValid

                        if (isTitleInputValid && isCategoryInputValid && selectedImageUri != null) {
                            isCompleteBtnEnabled = false
                            onCompleteBtnClick()
                            val request = RequestUploadGalleryDto(
                                category = selectedCategory,
                                title = titleValue,
                                content = galleryUploadDescription
                            )

                            val imageUri = selectedImageUri

                            if (imageUri != null) {
                                val imagePart = createImagePart(contentResolver, imageUri.toString())

                                if (imagePart != null) {
                                    try {
                                        galleryUploadViewModel.uploadGallery(
                                            imageUri = imageUri,
                                            request = request,
                                            contentResolver = contentResolver
                                        )
                                    } catch (e: Exception) {
                                        Log.e("GalleryUpload", "Error uploading gallery", e)
                                    }
                                } else {
                                    Log.e("GalleryUpload", "Invalid image part")
                                }
                            } else {
                                Log.e("GalleryUpload", "Image URI is null")
                            }
                        }
                    }
                }
            )
        }
    }
}