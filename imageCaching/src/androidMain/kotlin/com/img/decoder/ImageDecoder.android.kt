package com.img.decoder

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel


internal actual object ImageBitmapDecoder : ImageDecoder<ImageBitmap> {


    override suspend fun decode(byteChannel: ByteReadChannel): ImageBitmap {
        val bytes = byteChannel.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            ?: throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
        return bitmap.asImageBitmap()
    }

}
