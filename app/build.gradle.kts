import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "pl.srw.movies"
        minSdkVersion(21)
        targetSdkVersion(29)
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")

    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")

    implementation("androidx.appcompat:appcompat:${Androidx.appcompat}")
    implementation("androidx.core:core-ktx:${Androidx.core}")
    implementation("androidx.constraintlayout:constraintlayout:${Androidx.constraintlayout}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Androidx.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Androidx.lifecycle}")

    testImplementation("junit:junit:$junit")

    androidTestImplementation("androidx.test.ext:junit:${Androidx.Test.junit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Androidx.Test.espresso}")
}
