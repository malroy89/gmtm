plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

val minSdkValue: Int by rootProject.extra
val compileSdkValue: Int by rootProject.extra
val targetSdkValue: Int by rootProject.extra

android {
    compileSdk = compileSdkValue

    defaultConfig {
        applicationId = "de.malroy.givemethemovie"
        minSdk = minSdkValue
        targetSdk = targetSdkValue
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TMDB_API_KEY", "\"${getPropValue("GMTM_TMDB_API_KEY")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.kotlin.stdlib)

    implementation(libs.retrofit)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.0.4")
}

fun getPropValue(propertyName: String): Any {
    return project.findProperty(propertyName) ?: throw groovy.lang.MissingPropertyException("Property $propertyName not found! Add it to your ~/.gradle/gradle.properties")
}
