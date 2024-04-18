package com.img.cache.ui.vm

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import cafe.adriel.voyager.core.model.screenModelScope
import com.img.cache.domain.cache.AppLRUCache
import com.img.cache.ui.model.ResultState
import com.img.cache.domain.usecase.My_CacheImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ImageRequestViewModel(
    private val cacheImageUseCase: My_CacheImageUseCase

    ) : BaseViewModel<List<ResultState>>() {

    private val lruCache: AppLRUCache<String, ImageBitmap> = AppLRUCache(10)

    override fun createInitialState(): List<ResultState> {
        return listOf(ResultState.Loading)
    }



    @Composable
    fun loadPainter(imgUrl: String) {


    }

    fun loadImage(imgUrl: String) {
        screenModelScope.launch(Dispatchers.IO) {
            cacheImageUseCase(imgUrl)
                .onSuccess {
                    println("ImageRequestViewModel loadImage: $it")
                    if (it != null) {
                    setState {
//                            ResultState.Success(it)
                        this.plus(ResultState.Success(it))
                        }
                    }
                }
                .onFailure {
                    println("ImageRequestViewModel loadImage: $it")
                }
        }
    }



}