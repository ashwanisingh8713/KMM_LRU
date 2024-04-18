package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

interface My_IRepository {

    suspend fun getImageFromRemote(url: String):String
    suspend fun getImageFromCache(url: String): ImageBitmap?
}