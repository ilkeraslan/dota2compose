buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
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
