package com.img.app.domain.repo

import com.img.app.domain.model.UnSplashData


interface IRemoteRepository {
    suspend fun getDataFromRemote(): List<UnSplashData>
}