plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // Modules
    implementation(project(":domain"))

    // ':data:remote' module "api"s its dependencies to ':data'

    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.8.0")

    // Coroutines
    // TODO: introduce 'base' module with common dependencies and "api" them.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}