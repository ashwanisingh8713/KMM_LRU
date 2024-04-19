package com.img.cache.ui.vm

import com.img.cache.ui.model.ImageResultState
import com.img.cache.domain.usecase.My_CacheImageUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

internal class ImageRequestState(
    private val cacheImageUseCase: My_CacheImageUseCase
    ) : BaseRequestState<ImageResultState>() {

    override fun createInitialState(): ImageResultState {
        return ImageResultState.Loading
    }


    fun loadImage(imgUrl: String, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            cacheImageUseCase(imgUrl)
                .onSuccess {
                    it?.let {
                        setState {
                            ImageResultState.Success(it)
                        }
                    }


                }
                .onFailure {
                    println("ImageRequestViewModel loadImage: $it")
                }
        }
    }



}