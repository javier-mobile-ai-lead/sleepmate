plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)

}

android {
    namespace = "com.sleepmate.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sleepmate.app"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    
    // Navigation
    implementation(libs.navigation.compose)
    
    // ViewModels
    implementation(libs.lifecycle.viewmodel.compose)
    implementation("androidx.lifecycle:lifecycle-service:2.9.2")
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.installations)
    implementation(libs.firebase.config)
    implementation(libs.firebase.messaging)

    //OneSignal
    implementation("com.onesignal:OneSignal:[5.1.0, 5.1.99]")

    
    // YouTube Player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    implementation("com.google.android.gms:play-services-auth:21.1.0")
    
    // AdMob
    implementation(libs.play.services.ads)

    // Timber
    implementation(libs.timber)
    
    // Splash Screen
    implementation(libs.splashscreen)
    
    // DataStore
    implementation(libs.datastore.preferences)

    //new
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.play.services.location)
    implementation(libs.mlkit.text.recognition)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.airbnb.android:lottie-compose:6.3.0")
}