plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Deps.Androidx.lifecycleEx)

    api(Deps.Test.junit)
    api(Deps.Test.mockitoKt)
    api(Deps.Test.mockitoInline)
    api(Deps.Test.kluent)
}
