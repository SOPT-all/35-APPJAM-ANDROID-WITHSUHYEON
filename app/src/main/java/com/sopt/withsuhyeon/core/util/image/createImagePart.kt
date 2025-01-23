package com.sopt.withsuhyeon.core.util.image

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import okhttp3.MultipartBody

fun createImagePart(contentResolver: ContentResolver, uriString: String?): MultipartBody.Part? {
    return try {
        uriString?.let {
            val uri = Uri.parse(it)
            val imageRequestBody = ContentUriRequestBody(contentResolver, uri)
            imageRequestBody.toMultiPartData("image")
        }
    } catch (e: Exception) {
        Log.e("createImagePart", "Error creating image part: ${e.message}")
        null
    }
}