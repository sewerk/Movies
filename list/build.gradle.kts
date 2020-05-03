plugins {
    id("android-kotlin-library")
}

dependencies {
    implementation(project(":commons"))
    implementation(Deps.Androidx.recyclerView)
    implementation(Deps.retrofit)
    kapt(Deps.moshiKotlinCodegen)
    implementation(Deps.moshiKotlin)
}