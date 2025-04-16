// build.gradle.kts (nivel proyecto)



buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ← necesario para MaterialCalendarView
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        // Aquí van las dependencias del buildscript si necesitas alguna
        classpath("com.android.tools.build:gradle:8.9.0") // o la versión que estés usando
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0") // ejemplo
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}