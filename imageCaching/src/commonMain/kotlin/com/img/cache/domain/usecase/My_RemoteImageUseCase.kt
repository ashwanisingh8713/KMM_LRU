package com.img.cache.domain.usecase

import com.img.cache.domain.repo.My_IRepository
import com.img.cache.domain.usecase.base.MyBaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class My_RemoteImageUseCase(private val iRepository: My_IRepository, dispatcher: CoroutineDispatcher)
    : MyBaseUseCase<String, String>(dispatcher) {
    override suspend fun run(param: String): String {
        return iRepository.getImageFromRemote(param)
    }
}