plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.13" apply false
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.10"))
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.20")
    }
}