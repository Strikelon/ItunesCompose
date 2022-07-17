
object Dependencies {

    object Kotlin {
        const val version = "1.6.10"
    }

    object Gradle {
        const val version = "7.2.1"
    }

    object Compose {
        const val version = "1.2.0-alpha05"
        const val ui = "androidx.compose.ui:ui:$version"
        const val tooling = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material = "androidx.compose.material:material:$version"
        const val toolingTest = "androidx.compose.ui:ui-tooling:$version"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:2.1.0"
    }

    object Hilt {
        const val version = "2.40.5"
        const val android = "com.google.dagger:hilt-android:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:2.4.2"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val material = "com.google.android.material:material:1.4.0"
        const val multidex = "androidx.multidex:multidex:2.0.0"
    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0"
        const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
    }

    object Coroutines {
        const val coroutinesKotlinx = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
    }

    object OkHttp {
        const val version = "4.9.1"
        const val okHttp3 = "com.squareup.okhttp3:okhttp:$version"
        const val okHttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        const val version = "2.9.0"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$version"
        const val retrofit2MoshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        const val version = "1.13.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiKotlinAnnotation = "com.squareup.moshi:moshi-kotlin-codegen:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
    }

    object Test {
        const val jUnit = "junit:junit:4.+"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}