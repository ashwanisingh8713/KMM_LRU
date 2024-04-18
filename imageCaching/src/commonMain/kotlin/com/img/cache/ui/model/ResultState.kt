package com.img.cache.ui.model

import androidx.compose.ui.graphics.ImageBitmap

sealed class ResultState{
    data object Loading: ResultState()
    data class Success(val bitmap: ImageBitmap): ResultState()
    data class Failure(val errorMsg: String): ResultState()
    data class PlaceHolder(val drawable: String): ResultState()
}
infix fun String.concate(msg: String): String {
    return "$this $msg"
}

fun String.addNameInitial(): String {
    return "Mr. $this"
}