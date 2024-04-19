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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import com.img.cache.ui.compose.KamelImage


private const val ItemsCount: Int = 1000

class GalleryScreen: Screen {



    override val key: ScreenKey = uniqueScreenKey

    private fun generateRandomImageUrl(seed: Int = (1..1000).random()): String =
        "https://picsum.photos/seed/$seed/500/500"

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<GalleryViewModel>()
        val states = viewModel.resultState.collectAsState()

        /*when (val state = states.value) {
            is UIState.Success -> {
                Gallery(items = state.data)
            }
            is UIState.Loading -> {
                // Loading
            }
            is UIState.Failure -> {
                // Error
            }
        }*/


        var items by remember {
            mutableStateOf(
                List(ItemsCount) {
                    generateRandomImageUrl(it)
                }
            )
        }

        Gallery(items = items)

    }

    @Composable
    fun Gallery(items: List<String>) {


        Box(Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(items) { imageUrl ->
                    KamelImage(
                        url = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1F)
                            .padding(8.dp)
                            .shadow(elevation = 8.dp, RoundedCornerShape(16.dp))
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }



    }

}

