plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id ("dagger.hilt.android.plugin")
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
        release {
            isMinifyEnabled = true
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

    // compose
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.tooling)

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

    androidTestImplementation(Dependencies.Compose.uiTest)
    debugImplementation(Dependencies.Compose.toolingTest)
}