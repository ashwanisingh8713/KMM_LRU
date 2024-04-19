# Memory Image Caching Compose Multiplatform Application

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

## Code Architecture Details
 - The project is built with Kotlin Multiplatform Mobile (KMM) and uses Jetpack Compose for UI.
 - The project is divided into 4 modules: 
   1.`imageCaching` - This is Image Caching shared module library that contains the core logic of the image caching
     - `imageCaching/commonMain` - Shared code for Android and iOS,
     - `imageCaching/androidMain` - Android Image Decoder,
     - `imageCaching/iosMain` - iOS Image Decoder .
   2.`composeApp/commonMain` - Shared app code for Android and iOS, 
   3.`composeApp/androidApp` - Android specific code, 
   4.`iosApp` - iOS specific code.

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



