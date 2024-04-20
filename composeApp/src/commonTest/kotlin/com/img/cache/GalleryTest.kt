package com.img.cache

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.koin.getScreenModel
import com.img.app.domain.model.UnSplashData
import com.img.app.domain.model.Urls
import com.img.app.ui.GalleryScreen
import com.img.app.ui.GalleryViewModel
import com.img.app.ui.UIState
import com.img.app.ui.compose.ErrorUI
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GalleryTest {

    @Test
    fun checkLoadingUIVisible() = runComposeUiTest {
        val gallery = GalleryScreen()
        setContent {
            gallery.Content()
            val viewModel = gallery.getScreenModel<GalleryViewModel>()
            val state = viewModel.resultState.collectAsState()
            viewModel.setState { UIState.Loading }
            assertEquals(state.value, UIState.Loading)
        }
        onNodeWithTag("LoadingUI").assertExists()
    }

    @Test
    fun checkSuccessRemoteData() = runComposeUiTest {
        val gallery = GalleryScreen()
        setContent {
            val viewModel = gallery.getScreenModel<GalleryViewModel>()
            val state = viewModel.resultState.collectAsState()
            viewModel.setState {
                UIState.Success(
                    listOf(
                        UnSplashData("1", "1", urls = Urls("1", "1", "1", "1", "")),
                        UnSplashData("1", "1", urls = Urls("1", "1", "1", "1", "")),
                        UnSplashData("1", "1", urls = Urls("1", "1", "1", "1", "")),
                    )
                )
            }
            if(state.value is UIState.Success) {
                assertEquals((state.value as UIState.Success).uiRemoteData.size, 3)
            }

        }
    }

    @Test
    fun checkSuccessUILoadedAndVisible() = runComposeUiTest {
        val gallery = GalleryScreen()
        setContent {
            gallery.Content()
            val viewModel = gallery.getScreenModel<GalleryViewModel>()
            viewModel.setState {
                UIState.Success(
                    listOf(
                        UnSplashData("1", "Description 1", urls = Urls("raw 1", "full 1", "reg 1", "small 1", "thumb 1")),
                        UnSplashData("2", "Description 2", urls = Urls("raw 2", "full 2", "reg 2", "small 2", "thumb 2")),
                        UnSplashData("3", "Description 3", urls = Urls("raw 3", "full 3", "reg 3", "small 3", "thumb 3")),
                    )
                )
            }
        }

        onNodeWithTag("Gallery").assertExists()
    }

    @Test
    fun checkEmptyUI() = runComposeUiTest {
        val emptyMsg = "No Data Found"
        lateinit var state: State<UIState>
        setContent {
            ErrorUI(msg = emptyMsg)
            val viewModel = GalleryScreen().getScreenModel<GalleryViewModel>()
            state = viewModel.resultState.collectAsState()
            viewModel.setState { UIState.Failure("No Data Found") }
        }

        onNodeWithTag("ErrorUIText").assertTextEquals((state.value as UIState.Failure).errorMsg)
    }

}