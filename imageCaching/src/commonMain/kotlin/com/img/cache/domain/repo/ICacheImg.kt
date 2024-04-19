package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

interface ICacheImg {
    suspend fun getImageFromCache(url: String): ImageBitmap?
}