package com.luisfelipe.buildSrc

object Dependencies {

    object Gradle {
        const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    }

    object Core {
        const val coreKtx = "androidx.core:core-ktx:${Versions.core}"

        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"

        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object LifecycleAwareComponents {
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
        const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewModel}"
    }

    object UI {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        const val material =
            "com.google.android.material:material:${Versions.material_version}"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

        const val lottieAnimation = "com.airbnb.android:lottie:${Versions.lottieAnimation}"

        const val appCompat = "androidx.appcompat:appcompat:${Versions.app_compat}"

        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"

        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    }

    object Data {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object DI {
        const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        const val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
        const val daggerHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHiltCompiler}"
    }

    object Testing {
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
        const val junit = "junit:junit:${Versions.junit}"
        const val junitAndroid = "androidx.test.ext:junit:${Versions.junitAndroid}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

}