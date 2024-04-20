package com.img.cache.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.img.cache.ui.model.ImageResultState
import com.img.cache.ui.vm.ImageRequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

val scope = CoroutineScope(Dispatchers.IO)

@Composable
fun CacheImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    onLoading: @Composable (BoxScope.() -> Unit)? = null,
    onFailure: @Composable (BoxScope.(String) -> Unit)? = null,
    contentAlignment: Alignment = Alignment.Center,
) {

    val viewModel = getKoin().get<ImageRequestState>()

    scope.launch {viewModel.loadImage(url, this)  }
    /*LaunchedEffect(viewModel) {
        viewModel.loadImage(url, this)
    }*/

    viewModel.resultState.collectAsState()

    val onSuccess: @Composable (BoxScope.(Painter) -> Unit) = { painter ->
        Image(
            painter,
            contentDescription,
            Modifier.fillMaxSize(),
            alignment,
            contentScale,
            alpha,
            colorFilter
        )
    }

    CacheImageBox(
        viewModel.resultState.collectAsState().value,
        modifier,
        contentAlignment,
        onLoading,
        onFailure,
        onSuccess,
    )

}



@Composable
private fun CacheImageBox(
    state: ImageResultState,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onLoading: @Composable (BoxScope.() -> Unit)? = null,
    onFailure: @Composable (BoxScope.(String) -> Unit)? = null,
    onSuccess: @Composable BoxScope.(Painter) -> Unit,
) {

    Box(modifier, contentAlignment) {
            when (state) {
                is ImageResultState.Loading -> if (onLoading != null) onLoading()
                is ImageResultState.Success -> onSuccess(BitmapPainter(state.bitmap))
                is ImageResultState.Failure -> if (onFailure != null) onFailure(state.errorMsg)
                is ImageResultState.PlaceHolder -> {}
            }

    }
}