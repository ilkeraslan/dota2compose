plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.4.30"
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "me.ilker.dota2compose"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta08"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("io.coil-kt:coil-compose:1.3.0")
    implementation("com.google.android.material:material:1.4.0")

    implementation("androidx.compose.compiler:compiler:1.0.0-rc02")
    implementation("androidx.compose.ui:ui:1.0.0-rc02")
    implementation("androidx.compose.runtime:runtime:1.0.0-rc02")
    implementation("androidx.compose.material:material:1.0.0-rc02")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-rc02")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha04")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-rc02")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.google.dagger:hilt-android:2.36")
    kapt("com.google.dagger:hilt-compiler:2.36")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
