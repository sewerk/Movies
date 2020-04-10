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

val junitVersion: String by project
val appcompatVersion: String by project
val androidCoreVersion: String by project
val constraintlayoutVersion: String by project
val lifecycleVersion: String by project
val androidTestVersion: String by project
val espressoVersion: String by project

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")

    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.core:core-ktx:$androidCoreVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayoutVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    testImplementation("junit:junit:$junitVersion")

    androidTestImplementation("androidx.test.ext:junit:$androidTestVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
}
