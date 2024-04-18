import kotlin.reflect.KClass

/*
actual fun decodeImage(data: ByteReadChannel): ImageBitmap {
    val bytes = data.toByteArray()
    return try {
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (t: Throwable) {
        throw throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
    }
}*/

internal actual object ImageBitmapDecoder : Decoder<ImageBitmap> {

    override val outputKClass: KClass<ImageBitmap> = ImageBitmap::class

    override suspend fun decode(
        channel: ByteReadChannel, resourceConfig: ResourceConfig
    ): ImageBitmap {
        val bytes = channel.toByteArray()
        return try {
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        } catch (t: Throwable) {
            throw throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
        }
    }

}
