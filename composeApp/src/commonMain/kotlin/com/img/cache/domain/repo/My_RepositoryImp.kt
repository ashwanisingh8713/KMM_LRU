package com.img.cache.domain.repo

import androidx.compose.ui.graphics.ImageBitmap

class My_RepositoryImp(
    private val cacheData: My_ICacheData,
    private val remoteData: My_IRemoteData
) : My_IRepository {

    override suspend fun getImageFromRemote(url: String) =
        remoteData.getImageFromRemote(url)

    override suspend fun getImageFromCache(url: String): ImageBitmap? =
        cacheData.getImageFromCache(url)
}