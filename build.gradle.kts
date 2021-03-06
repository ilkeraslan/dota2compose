buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.36")
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
