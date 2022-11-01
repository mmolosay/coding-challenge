plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = "com.leverx.challenge"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.leverx.news"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(":core"))
    implementation(project(":domain"))

    // Jetpack
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")

    // Compose
    implementation("androidx.compose.ui:ui:1.3.0")
    implementation("androidx.compose.ui:ui-tooling:1.3.0")
    implementation("androidx.compose.foundation:foundation:1.3.0")
    implementation("androidx.navigation:navigation-compose:2.5.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.0-alpha01")

    // Design
    implementation("androidx.compose.material3:material3:1.0.0")

    // Graphics
    implementation("androidx.compose.material:material-icons-core:1.3.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")

    // Material
    implementation("com.google.android.material:material:1.7.0") // for activity theme

    // Coil (image loading)
    implementation("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-compose:2.2.2")

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

    // Testing

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test:core:1.4.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // MockK
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.4")
}