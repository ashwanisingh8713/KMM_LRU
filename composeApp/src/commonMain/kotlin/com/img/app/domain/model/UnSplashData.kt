package com.img.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
class Sample : List<UnSplashData> by ArrayList()
@Serializable
data class UnSplashData(val id: String, val alt_description: String, val urls: Urls)

@Serializable
data class Urls(val raw: String, val full: String, val regular: String, val small: String, val thumb: String)
