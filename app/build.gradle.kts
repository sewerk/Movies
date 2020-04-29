plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        applicationId = "pl.srw.movies"
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding.isEnabled = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":list"))
    implementation(Deps.kotlinStdlibJdk7)
    implementation(Deps.koinViewModel)

    implementation(Deps.Androidx.appCompat)
    implementation(Deps.Androidx.coreKtx)
    implementation(Deps.Androidx.constraintLayout)
    implementation(Deps.Androidx.lifecycleEx)
    implementation(Deps.Androidx.lifecycleViewModelKtx)

    testImplementation(Deps.junit)

    androidTestImplementation(Deps.Androidx.Test.junit)
    androidTestImplementation(Deps.Androidx.Test.espressoCore)
}
