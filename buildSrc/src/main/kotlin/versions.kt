const val kotlinVersion = "1.3.71"
const val koinVersion = "2.1.5"
const val androidGradlePlugin = "3.6.3"

object Android {
    const val minSdk = 21
    const val targetSdk = 29
}

object Deps {
    const val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    const val glide = "com.github.bumptech.glide:glide:4.10.0"
    const val junit = "junit:junit:4.13"

    object Androidx {
        private const val lifecycle = "2.2.0"
        private const val navigation = "2.3.0-alpha05"

        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val lifecycleEx = "androidx.lifecycle:lifecycle-extensions:$lifecycle"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle"
        const val dataBindingCompiler = "com.android.databinding:compiler:$androidGradlePlugin"

        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigation"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigation"

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.1"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }
}