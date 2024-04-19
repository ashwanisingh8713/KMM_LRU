package com.img.app.data

import com.img.app.domain.model.UnSplashData
import com.img.app.domain.repo.IRemoteRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RemoteDataImp(private val httpClient: HttpClient, private val endPoint: String):
    IRemoteRepository {
    override suspend fun getDataFromRemote(): UnSplashData {
        return httpClient.get(endPoint).body<UnSplashData>()
    }
}