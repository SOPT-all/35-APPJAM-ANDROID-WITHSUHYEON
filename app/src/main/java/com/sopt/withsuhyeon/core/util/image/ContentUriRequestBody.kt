package com.sopt.withsuhyeon.core.util.image

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.ByteArrayOutputStream

class ContentUriRequestBody(
    private val contentResolver: ContentResolver,
    private val uri: Uri,
) : RequestBody() {
    private var fileName = ""
    private var size = -1L
    private var compressedImage: ByteArray? = null

    init {
        contentResolver.query(
            uri,
            arrayOf(MediaStore.Images.Media.SIZE, MediaStore.Images.Media.DISPLAY_NAME),
            null,
            null,
            null,
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                fileName =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
            }
        }

        compressBitmap()
    }

    private fun compressBitmap() {
        val originalSizeMb = size / (1024.0 * 1024.0)
        // 1) 먼저 Exif 정보 읽기
        val exif = contentResolver.openInputStream(uri)?.use { ExifInterface(it) } ?: return

        // 2) inJustDecodeBounds = true 설정해서 이미지 크기만 읽어오기
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        contentResolver.openInputStream(uri)?.use { input ->
            BitmapFactory.decodeStream(input, null, options)
        }
        // 이제 options.outWidth, options.outHeight 가 세팅됨

        // 3) 실제 디코딩을 위한 inSampleSize 계산
        val (width, height) = options.run { outWidth to outHeight }
        val needDownSample = (originalSizeMb >= IMAGE_SIZE_MB)
        if (needDownSample) {
            options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT)
        }

        // 4) inJustDecodeBounds = false 로 다시 설정
        options.inJustDecodeBounds = false

        // 5) 실제 이미지를 다시 decodeStream
        val originalBitmap = contentResolver.openInputStream(uri)?.use { input ->
            BitmapFactory.decodeStream(input, null, options)
        } ?: return

        // 6) 회전 처리
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        val rotatedBitmap = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(originalBitmap, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(originalBitmap, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(originalBitmap, 270f)
            else -> originalBitmap
        }
        if (rotatedBitmap != originalBitmap) {
            originalBitmap.recycle()
        }

        // 7) JPEG 품질 조정 후 메모리에 저장
        val outputStream = ByteArrayOutputStream()
        val compressedOnce: ByteArray = outputStream.use {
            // 일단 100%로 시도 (또는 적당히 80%~90%로 시작)
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            it.toByteArray()
        }

        // 8) 만약 여기서도 1MB를 초과하면, “반복” 압축 전략(퀄리티 점차 낮추기)을 적용해본다
        var finalData = compressedOnce
        var compressQuality = 90
        while (finalData.size > 1_048_576 && compressQuality > 10) {
            val tempOut = ByteArrayOutputStream()
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, tempOut)
            val tempData = tempOut.toByteArray()

            // 더 작아졌으면 교체
            if (tempData.size < finalData.size) {
                finalData = tempData
            }
            compressQuality -= 10
        }

        rotatedBitmap.recycle()

        // 9) 최종 compressed 이미지 바이트 & 사이즈 세팅
        compressedImage = finalData
        size = finalData.size.toLong()
    }


    private fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix().apply {
            postRotate(degrees)
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int,
    ): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    private fun getFileName() = fileName

    override fun contentLength(): Long = size

    override fun contentType(): MediaType? =
        uri.let { contentResolver.getType(it)?.toMediaTypeOrNull() }

    override fun writeTo(sink: BufferedSink) {
        compressedImage?.let(sink::write)
    }

    fun toMultiPartData(name: String) = MultipartBody.Part.createFormData(name, getFileName(), this)

    companion object {
        const val MAX_WIDTH = 1024
        const val MAX_HEIGHT = 1024
        const val IMAGE_SIZE_MB = 1
    }
}