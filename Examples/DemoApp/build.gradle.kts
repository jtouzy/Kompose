import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

plugins {
    id("com.github.ben-manes.versions") version "0.27.0"
}

buildscript {
    repositories {
        jcenter()
        google()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(kotlin("gradle-plugin", Build.Versions.kotlin))
        classpath(kotlin("serialization", Build.Versions.kotlin))
        classpath(Build.androidGradle)
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.5.0")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-progressive", "-Xuse-experimental=kotlin.Experimental")
            jvmTarget = "1.8"
        }
    }
}

subprojects {
    afterEvaluate {
        extensions.configure<com.android.build.gradle.BaseExtension> {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }
}

// ./gradlew dependencyUpdates -Drevision=release
tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
//    resolutionStrategy {
//        componentSelection {
//            all {
//                val rejected =
//                    listOf("alpha", "beta", "rc", "cr", "m", "preview", "test", "b", "ea", "eap").any { qualifier ->
//                        candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
//                    }
//                if (rejected) {
//                    reject("Only final versions")
//                }
//            }
//        }
//    }
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
