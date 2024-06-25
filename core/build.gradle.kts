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
        buildConfigField("String", "BASE_URL", "\"https://api.tvmaze.com\"")
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
        debug{
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
        buildConfig = true
    }
}

dependencies {
    val roomVersion = rootProject.extra["room_version"]
    val retrofitVersion = rootProject.extra["retrofit_version"]
    val loggingInterceptorVersion = rootProject.extra["logging_interceptor_version"]
    val kotlinCoroutinesVersion = rootProject.extra["kotlin_coroutines_version"]
    val lifecycleVersion = rootProject.extra["lifecycle_version"]
    val jsoupVersion = rootProject.extra["jsoup_version"]
    val sqlcipherVersion = rootProject.extra["sqlcipher_version"]
    val sqliteVersion = rootProject.extra["sqlite_version"]



    //noinspection GradleDependency
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation ("androidx.room:room-runtime:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")

    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    api ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    implementation ("org.jsoup:jsoup:$jsoupVersion")

    implementation ("net.zetetic:android-database-sqlcipher:$sqlcipherVersion")
    implementation ("androidx.sqlite:sqlite-ktx:$sqliteVersion")
}