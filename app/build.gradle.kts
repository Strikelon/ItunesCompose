plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val API_BASE_URL = "API_BASE_URL"
        val CONFIG = { k: String -> "\"${project.properties[k]}\"" }

        debug {
            buildConfigField("String", API_BASE_URL, CONFIG("dev.api.base.url"))
        }
        release {
            isMinifyEnabled = true
            buildConfigField("String", API_BASE_URL, CONFIG("prod.api.base.url"))
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // android
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.multidex)

    // compose
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.tooling)

    // coil compose images
    implementation(Dependencies.Coil.coilCompose)

    // navigation
    implementation(Dependencies.Navigation.navigationCompose)

    // lifecycle
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.viewModelCompose)
    implementation(Dependencies.Lifecycle.activityCompose)
    implementation(Dependencies.Lifecycle.viewModelSavedState)

    // Hilt
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigation)
    kapt(Dependencies.Hilt.compiler)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesKotlinx)

    // Network
    implementation(Dependencies.OkHttp.okHttp3)
    implementation(Dependencies.OkHttp.okHttp3Interceptor)
    implementation(Dependencies.Retrofit.retrofit2)
    implementation(Dependencies.Retrofit.retrofit2MoshiConverter)

    // Moshi
    implementation(Dependencies.Moshi.moshi)
    kapt(Dependencies.Moshi.moshiKotlinAnnotation)
    implementation(Dependencies.Moshi.moshiKotlin)

    // test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    testImplementation(Dependencies.Coroutines.coroutinesTest)
    androidTestImplementation(Dependencies.Compose.uiTest)
    debugImplementation(Dependencies.Compose.toolingTest)
}
