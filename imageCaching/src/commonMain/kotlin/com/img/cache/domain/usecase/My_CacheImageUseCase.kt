package com.img.cache.domain.usecase

import androidx.compose.ui.graphics.ImageBitmap
import com.img.cache.domain.repo.ICacheImgRepository
import com.img.cache.domain.usecase.base.NetworkBaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class My_CacheImageUseCase(private val iRepository: ICacheImgRepository, dispatcher: CoroutineDispatcher)
    : NetworkBaseUseCase<String, ImageBitmap?>(dispatcher) {
    override suspend fun run(param: String): ImageBitmap? {
        return iRepository.getImageFromCache(param)
    }
}