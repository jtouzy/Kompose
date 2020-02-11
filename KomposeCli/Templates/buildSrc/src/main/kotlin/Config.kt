/*
 * Copyright $$YEAR$$ $$AUTHOR$$
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Build {

    object Versions {
        const val kotlin = "$$VERSION_KOTLIN$$"
        const val androidGradle = "$$VERSION_ANDROID_GRADLE$$"
        const val xcodeSync = "$$VERSION_XCODE_SYNC$$"
    }

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val xcodeSync = "co.touchlab:kotlinxcodesync:${Versions.xcodeSync}"
}

object Android {
    const val minSdkVersion = $$VERSION_ANDROID_MIN_SDK$$
    const val targetSdkVersion = $$VERSION_ANDROID_TARGET_SDK$$
    const val compileSdkVersion = $$VERSION_ANDROID_COMPILE_SDK$$
}

object Libs {

    private object Versions {
        const val timber = "$$VERSION_LIBS_TIMBER$$"
        const val koin = "$$VERSION_LIBS_KOIN$$"
        const val coil = "$$VERSION_LIBS_COIL$$"
        const val jsr310 = "$$VERSION_LIBS_JSR310$$"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val koinAndroid = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val jsr310 = "com.jakewharton.threetenabp:threetenabp:${Versions.jsr310}"
}


object AndroidX {

    private object Versions {
        const val appCompat = "$$VERSION_ANDROIDX_APPCOMPAT$$"
        const val coreKtx = "$$VERSION_ANDROIDX_COREKTX$$"
        const val compose = "$$VERSION_ANDROIDX_COMPOSE$$"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val composeFoundation = "androidx.ui:ui-foundation:${Versions.compose}"
    const val composeFramework = "androidx.ui:ui-framework:${Versions.compose}"
    const val composeLayout = "androidx.ui:ui-layout:${Versions.compose}"
    const val composeMaterial = "androidx.ui:ui-material:${Versions.compose}"
    const val composeTooling = "androidx.ui:ui-tooling:${Versions.compose}"
}


object Coroutines {

    private const val version = "$$VERSION_KOTLINX_COROUTINES$$"

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
}

object Serialization {

    private const val version = "$$VERSION_KOTLINX_SERIALIZATION$$"

    const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
    const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$version"
}

object Ktor {

    private const val version = "$$VERSION_KTOR$$"

    // Common
    const val clientCore = "io.ktor:ktor-client-core:$version"
    const val clientJson = "io.ktor:ktor-client-json:$version"
    const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
    const val clientLogging = "io.ktor:ktor-client-logging:$version"
    // Android
    const val clientOkttp = "io.ktor:ktor-client-okhttp:$version"
    const val clientJsonJvm = "io.ktor:ktor-client-json-jvm:$version"
    const val clientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
    const val clientLoggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
    // IOS
    const val clientIos = "io.ktor:ktor-client-ios:$version"
    const val clientJsonNative = "io.ktor:ktor-client-json-native:$version"
    const val clientSerializationIos = "io.ktor:ktor-client-serialization-iosx64:$version"
    const val clientLoggingIos = "io.ktor:ktor-client-logging-native:$version"
}
