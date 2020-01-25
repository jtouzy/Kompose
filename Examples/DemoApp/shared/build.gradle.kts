import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.codingfeline.buildkonfig")
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

buildkonfig {
    packageName = "com.jtouzy.demo"

    defaultConfigs {
        buildConfigField(Type.STRING, "PUBLIC_KEY", project.properties["marvelPublicKey"] as String)
        buildConfigField(Type.STRING, "PRIVATE_KEY", project.properties["marvelPrivateKey"] as String)
    }
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
