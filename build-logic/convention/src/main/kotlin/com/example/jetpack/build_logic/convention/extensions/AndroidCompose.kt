package com.example.jetpack.build_logic.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureKotlinCompose(extension: CommonExtension<*,*,*,*,*,*>) {
    extension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            implementation(platform(bom))
            implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
            androidTestImplementation(platform(bom))
            debugImplementation(libs.findLibrary("androidx-ui-tooling").get())
        }

        testOptions {
            unitTests {
                // For Robolectric
                isIncludeAndroidResources = true
            }
        }
    }
}