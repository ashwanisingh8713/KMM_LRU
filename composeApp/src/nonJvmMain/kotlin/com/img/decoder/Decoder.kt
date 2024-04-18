
actual fun decodeImage(data: ByteReadChannel): ImageBitmap {
    val bytes = data.toByteArray()
    return try {
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (t: Throwable) {
        throw throw IllegalArgumentException("Failed to decode ${bytes.size} bytes to a bitmap. Decoded bytes:\n${bytes.decodeToString()}\n")
    }
}