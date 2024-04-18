package com.img.cache.domain.repo

interface My_IRemoteData {

    suspend fun getImageFromRemote(url: String):String
}