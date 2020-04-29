buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$androidGradlePlugin")
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
