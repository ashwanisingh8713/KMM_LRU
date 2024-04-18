package com.img.cache.data.repo

import com.img.cache.domain.repo.My_IRemoteData

class My_RemoteDataImp(private val httpClient: String): My_IRemoteData {
    override suspend fun getImageFromRemote(url: String): String {
        return ""
    }
}