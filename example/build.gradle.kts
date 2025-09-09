import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("fullstory")
}

val localPropertiesFile = rootProject.file("local.properties")
val properties = Properties()
if (localPropertiesFile.exists() && localPropertiesFile.canRead()) {
    properties.load(localPropertiesFile.reader())
}
val fullstoryOrg = (properties["fullstory_org"] as? String).orEmpty()

fullstory {
    org = fullstoryOrg
    enabledVariants = "all"
}

android {
    namespace = "com.survicate.fullstory.integration.example"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.survicate.fullstory.integration.example"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val survicateKey = (properties["survicate.key"] as? String).orEmpty()
        resValue("string", "survicate_key", survicateKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(project(mapOf("path" to ":lib")))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.survicate.sdk)
    implementation(libs.activity)
}
