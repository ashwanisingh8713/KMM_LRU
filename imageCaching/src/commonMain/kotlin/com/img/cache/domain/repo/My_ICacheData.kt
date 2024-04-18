package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

interface My_ICacheData {
    suspend fun getImageFromCache(url: String): ImageBitmap?
}