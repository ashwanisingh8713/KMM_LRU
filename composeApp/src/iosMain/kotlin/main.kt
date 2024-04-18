import androidx.compose.ui.window.ComposeUIViewController
import com.img.cache.App
import com.img.cache.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    initKoin()
    App()
}
