plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures.dataBinding = true
}

dependencies {
    kapt(Deps.Androidx.dataBindingCompiler)

    implementation(Deps.kotlinStdlibJdk7)
    implementation(Deps.koinViewModel)
    implementation(Deps.Androidx.appCompat)
    implementation(Deps.Androidx.lifecycleViewModelKtx)
    implementation(Deps.timber)

    testImplementation(project(":commons-test"))
}