import androidx.compose.ui.window.ComposeUIViewController
import com.img.app.App
import com.img.app.di.appDIModules
import com.img.cache.di.cacheSetup
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    iOSKoinSetup()
    App()
}

fun iOSKoinSetup() {
    startKoin {
        cacheSetup()
        loadKoinModules(appDIModules)
    }
}