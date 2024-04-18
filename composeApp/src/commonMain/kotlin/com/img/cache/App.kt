package com.img.cache

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.img.cache.theme.AppTheme
import com.img.cache.ui.GalleryScreen

@Composable
internal fun App() = AppTheme {
    Navigator(GalleryScreen())
}
