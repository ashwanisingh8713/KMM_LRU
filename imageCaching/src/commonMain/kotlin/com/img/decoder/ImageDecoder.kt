package com.img.decoder

import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.utils.io.ByteReadChannel

interface ImageDecoder<T : Any> {
     suspend fun decode(byteChannel: ByteReadChannel): T
}

internal expect object ImageBitmapDecoder : ImageDecoder<ImageBitmap>
