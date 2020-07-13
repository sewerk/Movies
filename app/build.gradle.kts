import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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

        buildConfigField("String", "API_KEY", gradleLocalProperties(rootDir).getProperty("apiKey"))
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures.dataBinding = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":list"))
    implementation(Deps.kotlinStdlibJdk7)
    implementation(Deps.koinViewModel)
    implementation(Deps.timber)

    implementation(Deps.Androidx.appCompat)
    implementation(Deps.Androidx.material)
    implementation(Deps.Androidx.navigationFragmentKtx)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitMoshiConverter)
    implementation(Deps.okHttpLoggingInterceptor)

    androidTestImplementation(Deps.Androidx.Test.junit)
    androidTestImplementation(Deps.Androidx.Test.espressoCore)
}
