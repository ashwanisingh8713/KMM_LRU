# Memory Image Caching Compose Multiplatform Application
This project is a simple image caching application that loads images from the network and caches them in memory.

<b>Supported platforms</b>: Android, iOS.

## Please make sure, before running the project:
- JDK 17 or higher on your machine
- Kotlin Multiplatform Mobile plugin installed in Android Studio
- `local.properties` file set a path to Android SDK
- To run the application on iOS, you need to have Xcode installed on your machine.

## About the Source Code Detail
 - I created a separate `imageCaching` module for image caching logic to make it reusable for other projects.
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
 - CLEAN & MVVM architecture pattern.
 - SOLID Principles.
 - The project uses Kotlin Coroutines for asynchronous programming.
 - Repository pattern is used to abstract the data layer.

## Used Techs
 - [Kotlin Multiplatform Mobile](https://kotlinlang.org/lp/mobile/)
 - [Jetpack Compose](https://developer.android.com/jetpack/compose)
 - [Ktor](https://ktor.io/)
 - [Koin](https://insert-koin.io/)
 - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
 - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
 - [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
 - [StateHoisting](https://developer.android.com/develop/ui/compose/state#state-hoisting)
 - [Expect and Actual](https://kotlinlang.org/docs/multiplatform-expect-actual.html)

## Handled Use Case
 - The application loads images from the network and caches them in memory.
 - The application displays a grid of images.
 - Network error handling and retry mechanism.
 - Image loading indicator.
 - Image placeholder.

## Compose Unit Tests
 - The project contains compose unit tests for the composeApp module.
 - `Android` - To Run simulator UI tests: `./gradlew :composeApp:pixel5Check`
 - `iOS` - To Run simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

## Android
 - To build, run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
 - Android simulator UI tests, run `./gradlew :composeApp:pixel5Check`
 - Android Screenshot
   * [Loading](https://github.com/ashwanisingh8713/KMM_LRU/blob/lib_working_ios/screenshots/Android_loading.png)
   * [Try Again](https://github.com/ashwanisingh8713/KMM_LRU/blob/lib_working_ios/screenshots/Android_tryAgain.png)
   * [Gallery](https://github.com/ashwanisingh8713/KMM_LRU/blob/lib_working_ios/screenshots/Android_Gallery.png)

## iOS
 - To run, `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`
 - iOS Screenshot
   * [Try Again](https://github.com/ashwanisingh8713/KMM_LRU/blob/lib_working_ios/screenshots/iOS_tryAgain.png)
   * [Gallery](https://github.com/ashwanisingh8713/KMM_LRU/blob/lib_working_ios/screenshots/iOS_Gallery.png)

### Limitations
 - [Unsplash loads maximum 30 images on each request](https://unsplash.com/documentation#pagination), so the gallery will show only 30 images.
 - As of now the application does not have a pagination feature.
 - API Key is hardcoded in the project, it should be stored in a secure place. If I would have not kept in the project, you would not be able to run the project directly.


