package com.sopt.withsuhyeon.feature.gallery

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun GalleryUploadWithPicker(
    onImageSelected: (Uri?) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectedImageUri = uri
        onImageSelected(uri)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
        onImageSelected(uri)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openGallery(photoPickerLauncher, galleryLauncher)
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity, Manifest.permission.READ_MEDIA_IMAGES
                )
            ) {
                context.showPermissionAppSettingsDialog()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 84.dp)
            .aspectRatio(1f)
            .background(color = colors.Grey500, shape = RoundedCornerShape(10.dp))
            .noRippleClickable {
                getGalleryPermission(
                    permissionLauncher = permissionLauncher,
                    photoPickerLauncher = photoPickerLauncher,
                    galleryLauncher = galleryLauncher
                )
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = selectedImageUri,
            contentDescription = stringResource(R.string.gallery_upload_image_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

fun getGalleryPermission(
    permissionLauncher: ActivityResultLauncher<String>,
    photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>,
    galleryLauncher: ActivityResultLauncher<String>
) {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
            openGallery(photoPickerLauncher, galleryLauncher)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        }
        else -> {
            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}

fun openGallery(
    photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>,
    galleryLauncher: ActivityResultLauncher<String>
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    } else {
        galleryLauncher.launch("image/*")
    }
}

fun Context.showPermissionAppSettingsDialog(
    title: String = "권한 요청",
    message: String = "앱의 모든 기능을 사용하려면 설정에서 권한을 허용해주세요.",
    positiveButtonText: String = "설정으로 이동",
    negativeButtonText: String = "취소"
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }
        .setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .show()
}

@Preview
@Composable
private fun PhotoPickerPreview() {
    WithSuhyeonTheme {
        GalleryUploadWithPicker {  }
    }
}