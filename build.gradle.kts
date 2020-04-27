buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath("org.koin:koin-gradle-plugin:$koinVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
