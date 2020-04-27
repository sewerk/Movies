plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    val kotlinVer = "1.3.71"
    implementation("com.android.tools.build:gradle:3.6.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
}
