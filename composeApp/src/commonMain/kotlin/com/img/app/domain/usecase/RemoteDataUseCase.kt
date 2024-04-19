package com.img.app.domain.usecase

import com.img.app.domain.model.UnSplashData
import com.img.app.domain.repo.IRemoteRepository
import com.img.cache.domain.usecase.base.NetworkBaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class RemoteDataUseCase(private val iRepository: IRemoteRepository,
                        dispatcher: CoroutineDispatcher)
    : NetworkBaseUseCase<Unit, List<UnSplashData>>(dispatcher) {
    override suspend fun run(param: Unit): List<UnSplashData> {
        return iRepository.getDataFromRemote()
    }
}