buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Dependencies.Gradle.version}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Kotlin.version}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Dependencies.Hilt.version}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
