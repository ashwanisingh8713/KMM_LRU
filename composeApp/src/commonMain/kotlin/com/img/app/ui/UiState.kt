package com.img.app.ui

import com.img.app.domain.model.UnSplashData

sealed class UIState{
    data object Loading: UIState()
    data class Success(val uiRemoteData: List<UnSplashData>): UIState()
    data class Failure(val errorMsg: String): UIState()
}