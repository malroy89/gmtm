buildscript {
    extra.apply {
        set("minSdkValue", 24)
        set("targetSdkValue", 31)
        set("compileSdkValue", 31)
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs
        classpath(libs.android.pluginGradle)
        classpath(libs.kotlin.pluginGradle)
        classpath(libs.hilt.pluginGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

