package com.img.cache.domain.usecase

import androidx.compose.ui.graphics.ImageBitmap
import com.img.cache.domain.repo.My_IRepository
import com.img.cache.domain.usecase.base.MyBaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class My_CacheImageUseCase(private val iRepository: My_IRepository, dispatcher: CoroutineDispatcher)
    : MyBaseUseCase<String, ImageBitmap?>(dispatcher) {
    override suspend fun run(param: String): ImageBitmap? {
        return iRepository.getImageFromCache(param)
    }
}