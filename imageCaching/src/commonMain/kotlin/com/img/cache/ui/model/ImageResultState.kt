package com.img.cache.ui.model

import androidx.compose.ui.graphics.ImageBitmap

sealed class ImageResultState{
    data object Loading: ImageResultState()
    data class Success(val bitmap: ImageBitmap): ImageResultState()
    data class Failure(val errorMsg: String): ImageResultState()
    data class PlaceHolder(val drawable: String): ImageResultState()
}