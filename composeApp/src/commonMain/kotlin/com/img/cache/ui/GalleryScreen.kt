package com.img.cache.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.img.cache.ui.model.ResultState
import com.img.cache.ui.vm.ImageRequestViewModel
import kotlinx.coroutines.launch
import org.koin.compose.getKoin


private const val ItemsCount: Int = 100

class GalleryScreen: Screen {

    override val key: ScreenKey = uniqueScreenKey

    public fun generateRandomImageUrl(seed: Int = (1..1000).random()): String =
        "https://picsum.photos/seed/$seed/500/500"

    @Composable
    override fun Content() {

//        val viewModel = getKoin().get<ImageRequestViewModel>()
        val viewModel = getKoin().get<ImageRequestViewModel>()
        /*LaunchedEffect(url) {
            viewModel.loadImage(url)
        }*/

        var items by remember {
            mutableStateOf(
                List(ItemsCount) {
                    generateRandomImageUrl(it)
                }
            )
        }

        Gallery(items = items, state = viewModel.resultState.collectAsState().value, viewModel)

    }

    @Composable
    fun Gallery(items: List<String>, state: ResultState, viewModel: ImageRequestViewModel) {

        val scope = rememberCoroutineScope()

        Box(Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(items) { imageUrl ->
//                    scope.launch { viewModel.loadImage(imageUrl) }
                    KamelImage(
                        state = state,
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

