plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlinx.kover") version "0.8.2"
}

android {
    namespace = "id.zoneordering.latihanciwithcircleci"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.zoneordering.latihanciwithcircleci"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kover {
    reports {
        // filters for all report types of all build variants
        filters {
            excludes {
                androidGeneratedClasses()
            }
        }

        variant("release") {
            // filters for all report types only of 'release' build type
            filters {
                excludes {
                    androidGeneratedClasses()

                    classes(
                        // excludes debug classes
                        "*.DebugUtil"
                    )
                }
            }
        }
    }

}