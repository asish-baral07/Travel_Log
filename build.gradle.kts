// Top-level build file where you can add configuration options common to all Sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // For DB
    id("com.google.devtools.ksp") version "2.3.4" apply false

    // Hilt
    id("com.google.dagger.hilt.android") version "2.59.2" apply false
}