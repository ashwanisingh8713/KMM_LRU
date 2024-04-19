package com.img.app

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.img.app.theme.AppTheme
import com.img.app.ui.GalleryScreen

@Composable
internal fun App() = AppTheme {
    Navigator(GalleryScreen())
}
