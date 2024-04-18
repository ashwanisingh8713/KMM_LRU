package com.img.decoder

import io.ktor.utils.io.ByteReadChannel
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.util.toByteArray
import org.jetbrains.skia.Image



internal actual object ImageBitmapDecoder : ImageDecoder<ImageBitmap> {

    override suspend fun decode(byteChannel: ByteReadChannel): ImageBitmap {
        val bytes = byteChannel.toByteArray()
        return bytes.toImageBitmap()
    }

}

fun ByteArray.toImageBitmap(): ImageBitmap =
    Image.makeFromEncoded(this).toComposeImageBitmap()

