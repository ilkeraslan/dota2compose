plugins {
    alias(libs.plugins.hilt.plugin).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
