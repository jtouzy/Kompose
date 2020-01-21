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
        const val androidGradle = "3.6.0-rc01"
        const val googleServices = "4.3.3"
    }

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
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
        const val uniflow = "0.9.3"
        const val coil = "0.9.2"
        const val jsr310 = "1.2.1"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val koinAndroid = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val uniflow = "io.uniflow:uniflow-androidx:${Versions.uniflow}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val jsr310 = "com.jakewharton.threetenabp:threetenabp:${Versions.jsr310}"
}

object Google {

    private object Versions {
        const val material = "1.1.0-beta02"
        const val firebaseCore = "17.2.1"
        const val firebaseMessaging = "20.0.1"
    }

    const val material = "com.google.android.material:material:${Versions.material}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
}


object AndroidX {

    private object Versions {
        const val appCompat = "1.1.0"
        const val recyclerView = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val lifecyleViewmodel = "2.1.0"
        const val coreKtx = "1.1.0"
        const val fragmentKtx = "1.1.0"
        const val navigation = "2.1.0"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecyleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecyleViewmodel}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}


object Coroutines {

    private const val version = "1.3.3"

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
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

    private const val version = "1.2.6"

    const val clientCore = "io.ktor:ktor-client-core:$version"
    const val clientJson = "io.ktor:ktor-client-json:$version"
    const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
    const val clientOkttp = "io.ktor:ktor-client-okhttp:$version"
    const val clientJsonJvm = "io.ktor:ktor-client-json-jvm:$version"
    const val clientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
    const val clientIos = "io.ktor:ktor-client-ios:$version"
    const val clientJsonNative = "io.ktor:ktor-client-json-native:$version"
    const val clientSerializationIos = "io.ktor:ktor-client-serialization-iosx64:$version"
}
