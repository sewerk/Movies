plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    val kotlin = "1.3.71"
    val androidGradlePlugin = "3.6.3"
    val junitPlugin = "1.6.0.0"

    implementation("com.android.tools.build:gradle:$androidGradlePlugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:$junitPlugin")
}
