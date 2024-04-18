package com.img.decoder

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import kotlin.reflect.KClass

/*
actual suspend fun <String> decodeImage(data: ByteReadChannel): String {
    val bytes = data.toByteArray()
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        ?: throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
//    return bitmap.asImageBitmap()
    return ""
}*/

internal actual object ImageBitmapDecoder : Decoder<ImageBitmap> {

    override val outputKClass: KClass<ImageBitmap> = ImageBitmap::class

    override suspend fun decode(channel: ByteReadChannel): ImageBitmap {
        val bytes = channel.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            ?: throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
        return bitmap.asImageBitmap()
    }

}

actual class Decoderr<T> {
    actual val outputKClass: Any
        get() = TODO("Not yet implemented")

    actual suspend fun decode(channel: ByteReadChannel): T {
        TODO("Not yet implemented")
    }
}