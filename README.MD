# Memory Image Caching Compose Multiplatform Application

## Before running! Need to remove KTOR extra instance
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

## Source Code Modules Details
 - The project is built with Kotlin Multiplatform Mobile (KMM) and uses Jetpack Compose for UI.
 - The project is divided into 4 modules: 
   - `imageCaching` - This is Image Caching shared module library that contains the core logic of the image caching
     - `imageCaching/commonMain` - Shared code for Android and iOS,
     - `imageCaching/androidMain` - Android Image Decoder,
     - `imageCaching/iosMain` - iOS Image Decoder .
   - `composeApp/commonMain` - Shared app code for Android and iOS, 
   - `composeApp/androidApp` - Android specific code, 
   - `iosApp` - iOS specific code.

## Code Architecture
 - The project uses Clean & MVVM architecture pattern.
 - The project uses Kotlin Coroutines for asynchronous programming.

## Used Techs
 - [Kotlin Multiplatform Mobile](https://kotlinlang.org/lp/mobile/)
 - [Jetpack Compose](https://developer.android.com/jetpack/compose)
 - [Ktor](https://ktor.io/)
 - [Koin](https://insert-koin.io/)
 - StateFlow
 - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
 - [Kotlin Serialization]
 - [StateHoisting](https://developer.android.com/develop/ui/compose/state#state-hoisting)


### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
Run android simulator UI tests: `./gradlew :composeApp:pixel5Check`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`



