package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

interface ICacheImgRepository {
    suspend fun getImageFromCache(url: String): ImageBitmap?
}