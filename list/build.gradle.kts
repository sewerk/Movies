plugins {
    id("android-kotlin-library")
}

dependencies {
    implementation(project(":commons"))
    implementation(Deps.Androidx.recyclerView)
}