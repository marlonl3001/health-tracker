plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization.plugin)
}

android {
    namespace = "br.com.mdr.auth"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.kotlin.serialization)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}