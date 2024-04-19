package com.img.app.domain.model

data class UnSplashData(val id: String, val alt_description: String, val urls: List<URLs>)

data class URLs(val raw: String, val full: String, val regular: String, val small: String, val thumb: String)
