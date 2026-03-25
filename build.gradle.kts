// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dokka.javadoc) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.fullstory.com")
    }
    dependencies {
        // FullStory Gradle plugin – use the latest version shown in release notes
        classpath(libs.fullstory.gradle.plugin)
    }
}
