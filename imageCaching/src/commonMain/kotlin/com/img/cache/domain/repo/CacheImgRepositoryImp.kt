package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

internal class CacheImgRepositoryImp(
    private val cacheData: ICacheImg,
) : ICacheImgRepository {


    override suspend fun getImageFromCache(url: String): ImageBitmap? =
        cacheData.getImageFromCache(url)
}