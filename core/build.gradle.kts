plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.leverx.challenge.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(":data"))
    implementation(project(":domain"))

    // Hilt
    /*
     * Error: The Hilt Android Gradle plugin is applied but no com.google.dagger:hilt-android dependency was found.
     * It is not possible to build Gradle without explicit "com.google.dagger:hilt-android"
     * dependency in specific module, so "api" is not applicable here.
     * You should add line below in each and every dependent module's build.gradle.
     */
    implementation("com.google.dagger:hilt-android:2.44")
    /*
     * Seems like there is no way to "api" Hilt compiler into dependent modules,
     * you should add line below in each and every dependent module's build.gradle
     */
    kapt("com.google.dagger:hilt-compiler:2.44")
}