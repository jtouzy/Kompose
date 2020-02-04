/*
 * Copyright 2019 Stéphane Baiget
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
        const val kotlin = "1.3.61"
        const val androidGradle = "4.0.0-alpha09"
        const val xcodeSync = "0.2"
    }

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val xcodeSync = "co.touchlab:kotlinxcodesync:${Versions.xcodeSync}"
}

object Android {
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
}

object Libs {

    private object Versions {
        const val timber = "4.7.1"
        const val koin = "2.0.1"
        const val coil = "0.9.2"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val koinAndroid = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object AndroidX {

    private object Versions {
        const val appCompat = "1.1.0"
        const val coreKtx = "1.1.0"
        const val viewModel = "2.1.0-beta01"
        const val compose = "0.1.0-dev03"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"

    const val composeFoundation = "androidx.ui:ui-foundation:${Versions.compose}"
    const val composeFramework = "androidx.ui:ui-framework:${Versions.compose}"
    const val composeLayout = "androidx.ui:ui-layout:${Versions.compose}"
    const val composeMaterial = "androidx.ui:ui-material:${Versions.compose}"
    const val composeTooling = "androidx.ui:ui-tooling:${Versions.compose}"
}


object Coroutines {

    private const val version = "1.3.3"

    const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
}

object Serialization {

    private const val version = "0.14.0"

    const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
    const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$version"
}

object Ktor {

    private const val version = "1.3.0"

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

object Tests {

    const val junit = "junit:junit:4.13"
    const val mockk = "io.mockk:mockk:1.9.3"
    const val androidxTestCore = "androidx.test:core:1.2.0"
    const val androidxTestJuint = "androidx.test.ext:junit:1.1.1"
    const val robolectric = "org.robolectric:robolectric:4.0"
}