import com.android.build.api.variant.BuildConfigField

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "me.ilker.dota2composer"
        minSdk = 26
        targetSdk = 34
        versionCode = 4
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    androidComponents {
        onVariants {
            it.buildConfigFields.put(
                /* key = */ "STEAM_API_KEY",
                /* value = */ BuildConfigField(
                    type = "String",
                    value = "\"${System.getenv("STEAM_API_KEY")}\"",
                    comment = "STEAM_API_KEY build config configuration"
                )
            )
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    namespace = "me.ilker.dota2composer"
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.bundles.compose)

    implementation(libs.rxkotlin)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.okhttp3)

    implementation(libs.android.hilt)
    implementation(libs.android.hilt.navigation.compose)
    kapt(libs.android.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}