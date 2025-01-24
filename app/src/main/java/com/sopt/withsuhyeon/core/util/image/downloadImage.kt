package com.sopt.withsuhyeon.core.util.image

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.OutputStream
import java.net.URL

fun downloadImage(context: Context, imageUrl: String, fileName: String, onSuccess: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val inputStream = URL(imageUrl).openStream()
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyApp")
            }

            val resolver = context.contentResolver
            val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                val outputStream: OutputStream? = resolver.openOutputStream(uri)
                outputStream.use { out ->
                    inputStream.copyTo(out!!)
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "다운로드 완료되었습니다", Toast.LENGTH_SHORT).show()
                    onSuccess()
                }
            } else {
                throw IOException("다운로드 실패했습니다")
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "다운로드 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
