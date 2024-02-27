plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.yogi.tvmazeapp.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
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
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val room_version = rootProject.extra["room_version"]
    val retrofit_version = rootProject.extra["retrofit_version"]
    val logging_interceptor_version = rootProject.extra["logging_interceptor_version"]
    val kotlin_coroutines_version = rootProject.extra["kotlin_coroutines_version"]
    val lifecycle_version = rootProject.extra["lifecycle_version"]
    val jsoup_version = rootProject.extra["jsoup_version"]
    val sqlcipher_version = rootProject.extra["sqlcipher_version"]
    val sqlite_version = rootProject.extra["sqlite_version"]



    //noinspection GradleDependency
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation ("androidx.room:room-runtime:$room_version")
    ksp ("androidx.room:room-compiler:$room_version")
    androidTestImplementation ("androidx.room:room-testing:$room_version")

    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines_version")
    implementation ("androidx.room:room-ktx:$room_version")
    api ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    implementation ("org.jsoup:jsoup:$jsoup_version")

    implementation ("net.zetetic:android-database-sqlcipher:$sqlcipher_version")
    implementation ("androidx.sqlite:sqlite-ktx:$sqlite_version")
}