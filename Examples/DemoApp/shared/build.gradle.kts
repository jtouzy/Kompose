import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("co.touchlab.kotlinxcodesync")
}

android {
    compileSdkVersion(Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        resConfigs("fr")
    }

    sourceSets {
        getByName("main").apply {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
        }
    }
}


kotlin {
    targets {
        android()
        val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

        iOSTarget("ios") {
            binaries {
                framework {
                    baseName = "shared"
                }
            }
        }
    }

    sourceSets {
        getByName("commonMain").dependencies {
            implementation(kotlin("stdlib-common", Build.Versions.kotlin))
            implementation(Coroutines.core)
            implementation(Serialization.common)
            implementation(Ktor.clientCore)
            implementation(Ktor.clientJson)
            implementation(Ktor.clientSerialization)
            implementation("com.soywiz.korlibs.krypto:krypto:1.9.1")
            implementation("com.soywiz.korlibs.klock:klock:1.8.6")
        }

        getByName("androidMain").dependencies {
            implementation(kotlin("stdlib", Build.Versions.kotlin))
            implementation(Coroutines.android)
            implementation(Serialization.runtime)
            implementation(Ktor.clientOkttp)
            implementation(Ktor.clientJsonJvm)
            implementation(Ktor.clientSerializationJvm)
            implementation(AndroidX.appCompat)
            implementation(AndroidX.coreKtx)
            implementation(Libs.jsr310)
        }

        getByName("iosMain").dependencies {
            implementation(Coroutines.native)
            implementation(Serialization.native)
            implementation(Ktor.clientIos)
            implementation(Ktor.clientJsonNative)
            implementation(Ktor.clientSerializationIos)
        }
    }
}

xcodeSync {
    projectPath = "../../iosApp/iosApp.xcodeproj"
    target = "iosApp"
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText(
            "#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n"
        )
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)
