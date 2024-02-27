// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    extra.apply {
        set("kotlin_version", "1.9.10")
        set("appcompat_version", "1.6.1")
        set("core_ktx_version","1.12.0")
        set("constraint_version", "2.1.4")
        set("legacy_support_version","1.0.0")
        set("junit_version","4.13.2")
        set("androidx_junit_version","1.1.5")
        set("espresso_version","3.5.1")
        set("multidex_version","2.0.1")

        set("cardview_version","1.0.0")
        set("recyclerview_version","1.3.2")
        set("material_version","1.11.0")
        set("glide_version","4.16.0")

        set("room_version","2.6.1")

        set("retrofit_version","2.9.0")
        set("logging_interceptor_version","4.12.0")
        set("kotlin_coroutines_version","1.8.0")
        set("lifecycle_version","2.7.0")
        set("jsoup_version", "1.14.3")

        set("koin_version", "3.5.3")
    }

    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.2.0")
        val kotlinVersion = rootProject.extra["kotlin_version"]
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.9.10-1.0.13")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


tasks.register("clean", Delete::class){
    delete(project.buildDir)
}