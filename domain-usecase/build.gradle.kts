
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}


android {
    namespace = "pl.wfranik.domain_usecase"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":domain-models"))
    implementation(project(":data-contract"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.dagger.android)
    ksp(libs.hilt.dagger.android.compiler)
    implementation(libs.timber)
    implementation(libs.kotlinx.serialization.json)
}
