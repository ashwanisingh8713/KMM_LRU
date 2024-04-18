package com.img.decoder

import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.utils.io.ByteReadChannel
import kotlin.reflect.KClass

public interface Decoder<T : Any> {

    /**
     * The KClass of the output of this decoder
     */
    public val outputKClass: KClass<T>

    /**
     * Decodes [channel] to [T].
     */
    public suspend fun decode(channel: ByteReadChannel): T
}

internal expect object ImageBitmapDecoder : Decoder<ImageBitmap>

expect class Decoderr<T> {
    val outputKClass: Any
    suspend fun decode(channel: ByteReadChannel): T
}