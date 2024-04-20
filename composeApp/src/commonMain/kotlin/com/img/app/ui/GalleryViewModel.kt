package com.img.app.ui

import cafe.adriel.voyager.core.model.screenModelScope
import com.img.app.domain.usecase.RemoteDataUseCase
import imagecaching.composeapp.generated.resources.Res
import imagecaching.composeapp.generated.resources.network_error_msg
import imagecaching.composeapp.generated.resources.no_data_found
import imagecaching.composeapp.generated.resources.offline
import imagecaching.composeapp.generated.resources.unable_to_resolve_host
import imagecaching.composeapp.generated.resources.unknown_error
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

class GalleryViewModel(private val remoteDataUseCase: RemoteDataUseCase): BaseViewModel<UIState>(){
    override fun createInitialState(): UIState {
        return UIState.Loading
    }

    @OptIn(InternalResourceApi::class)
    fun fetchRemoteData() {
        screenModelScope.launch {
            remoteDataUseCase(Unit)
                .onSuccess {
                    if(it.isEmpty()) {
                        val noData = getString(Res.string.no_data_found)
                        UIState.Failure(noData)
                    } else {
                        setState {
                            UIState.Success(it)
                        }
                    }
                }
                .onFailure {
                    var error = it.message
                    error = error?.let {it1->
                        if(it1.contains(getString(Res.string.offline)) || it1.contains(getString(Res.string.unable_to_resolve_host))) {
                            error = getString(Res.string.network_error_msg)
                        }
                        error
                    }
                    val unknown = getString(Res.string.unknown_error)
                    setState {
                        UIState.Failure( error?: unknown)
                    }
                }
        }
    }
}