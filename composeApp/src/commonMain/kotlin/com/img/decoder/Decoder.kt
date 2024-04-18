package com.img.decoder

import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.utils.io.ByteReadChannel

expect suspend fun decodeImage(data: ByteReadChannel): ImageBitmap