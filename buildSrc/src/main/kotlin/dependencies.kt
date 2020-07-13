object Versions {
    const val kotlin = "1.3.71"
    const val koin = "2.1.5"
    const val androidGradlePlugin = "4.0.0"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 29
}

object Deps {
    private const val retrofitVersion = "2.7.2"

    const val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val glide = "com.github.bumptech.glide:glide:4.10.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.2.2"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.9.2"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.9.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    object Androidx {
        private const val lifecycleVersion = "2.2.0"
        private const val navigationVersion = "2.3.0-alpha05"

        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val lifecycleEx = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.androidGradlePlugin}"

        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.1"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }

    object Test {
        const val junit = "org.junit.jupiter:junit-jupiter:5.6.2"
        const val mockitoInline = "org.mockito:mockito-inline:3.3.3"
        const val mockitoKt = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        const val kluent = "org.amshove.kluent:kluent-android:1.61"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.7"
    }
}