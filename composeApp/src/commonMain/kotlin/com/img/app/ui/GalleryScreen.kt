package com.img.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import com.img.app.domain.model.UnSplashData
import com.img.app.ui.compose.ErrorUI
import com.img.app.ui.compose.LoadingUI
import com.img.cache.ui.compose.CacheImage


class GalleryScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<GalleryViewModel>()
        val states = viewModel.resultState.collectAsState()

        LaunchedEffect(true) {
            viewModel.fetchRemoteData()
        }

        when (val state = states.value) {
            // Success
            is UIState.Success -> {
                Gallery(items = state.uiRemoteData)
            }

            is UIState.Loading -> {
                // Loading
                LoadingUI(modifier = Modifier.fillMaxSize())
            }

            is UIState.Failure -> {
                // Error
                ErrorUI(modifier = Modifier.fillMaxSize(), msg = state.errorMsg) {
                    viewModel.fetchRemoteData()
                }

            }
        }

    }

    @Composable
    fun Gallery(items: List<UnSplashData>) {

        Box(Modifier.fillMaxSize().testTag("Gallery")) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {

                items(items) { it ->
                    CacheImage(
                        scope = rememberCoroutineScope(),
                        url = it.urls.small,
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1F)
                            .padding(8.dp)
                            .shadow(elevation = 8.dp, RoundedCornerShape(16.dp))
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                        onLoading = {
                            //LoadingUI()
                        },
                        onFailure = {
                            //PlaceHolderUI()
                        },
                    )
                }
            }
        }


    }

}

