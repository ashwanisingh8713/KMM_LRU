package com.img.cache.ui.compose

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.koin.getScreenModel
import com.img.cache.ui.model.ResultState
import com.img.cache.ui.vm.ImageRequestViewModel
import kotlinx.coroutines.launch
import org.koin.compose.getKoin


@Composable
public fun KamelImage(
    state: ResultState,
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

    val viewModel = getKoin().get<ImageRequestViewModel>()
    val scope = rememberCoroutineScope()
    scope.launch {
        viewModel.loadImage(url)
    }

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

    KamelImageBox(
        viewModel.resultState.collectAsState().value,
        modifier,
        contentAlignment,
        onLoading,
        onFailure,
        onSuccess,
    )


}

/**
 * A composable that is used to display a [Painter] resource.
 * To load an image [Resource] asynchronously, use [asyncPainterResource].
 * @param resource The [Resource] that needs to be displayed.
 * @param modifier The modifier that is applied to the [Box].
 * @param contentAlignment The default alignment inside the Box.
 * @param animationSpec a [FiniteAnimationSpec] to be used in [Crossfade] animation, or null to be disabled.
 * @param onLoading Composable which is used while the image is in [Resource.Loading] state.
 * @param onFailure Composable which is used while the image is in [Resource.Failure] state.
 * @param onSuccess Composable which is used while the image is in [Resource.Success] state.
 */

@Composable
public fun KamelImageBox(
    state: ResultState,
    //resource: Resource<Painter>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onLoading: @Composable (BoxScope.() -> Unit)? = null,
    onFailure: @Composable (BoxScope.(String) -> Unit)? = null,
    onSuccess: @Composable BoxScope.(Painter) -> Unit,
) {


    Box(modifier, contentAlignment) {
            when (state) {
                is ResultState.Loading -> if (onLoading != null) onLoading()
                is ResultState.Success -> onSuccess(BitmapPainter(state.bitmap))
                is ResultState.Failure -> if (onFailure != null) onFailure(state.errorMsg)
                is ResultState.PlaceHolder -> {}
            }

    }
}