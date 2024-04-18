package com.img.decoder

import io.ktor.utils.io.ByteReadChannel
import platform.UIKit.UIImage
import kotlin.reflect.KClass
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.util.toByteArray
import org.jetbrains.skia.Image


actual class Decoderr<T> {
    actual val outputKClass: Any
        get() = TODO("Not yet implemented")

    actual suspend fun decode(channel: ByteReadChannel): T {
        TODO("Not yet implemented")
    }
}

internal actual object ImageBitmapDecoder : Decoder<ImageBitmap> {

    override val outputKClass: KClass<ImageBitmap> = ImageBitmap::class

    override suspend fun decode(channel: ByteReadChannel): ImageBitmap {
        val bytes = channel.toByteArray()
        return bytes.toImageBitmap()
    }
}

fun ByteArray.toImageBitmap(): ImageBitmap =
    Image.makeFromEncoded(this).toComposeImageBitmap()

