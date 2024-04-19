package com.img.app.ui

import cafe.adriel.voyager.core.model.screenModelScope
import com.img.app.domain.usecase.RemoteDataUseCase
import kotlinx.coroutines.launch

class GalleryViewModel(private val remoteDataUseCase: RemoteDataUseCase): BaseViewModel<UIState>(){
    override fun createInitialState(): UIState {
        return UIState.Loading
    }

    fun fetchRemoteData() {
        screenModelScope.launch {
            remoteDataUseCase(Unit)
                .onSuccess {
                    setState {
                        UIState.Success(it)
                    }
                }
                .onFailure {
                    setState {
                        UIState.Failure(it.message ?: "Unknown Error")
                    }
                }
        }
    }
}