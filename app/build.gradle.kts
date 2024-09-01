plugins {
    alias(libs.plugins.androidApplication)
    id("com.chaquo.python")
}

android {
    namespace = "com.example.youtubemusicmod"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.youtubemusicmod"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters += listOf("arm64-v8a", "x86_64")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

chaquopy{
    defaultConfig{
        pip{
            install("ytmusicapi")
        }
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    // For Json Processing
    implementation(libs.jackson.databind)
    implementation(libs.jackson.core)
    implementation(libs.jackson.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}