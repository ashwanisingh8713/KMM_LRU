package com.img.decoder

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel

actual suspend fun decodeImage(data: ByteReadChannel): ImageBitmap {
    val bytes = data.toByteArray()
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        ?: throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
    return bitmap.asImageBitmap()
}