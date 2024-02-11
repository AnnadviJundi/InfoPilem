plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.jundi.infopilem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jundi.infopilem"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.2" //PEMBARUAN FIREBASE DAN LAYOUT LOGIN

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.airbnb.android:lottie:6.3.0") // lottie
    implementation("com.ramotion.paperonboarding:paper-onboarding:1.1.3") // material design UI slider

    implementation("com.tbuonomo:dotsindicator:5.0") // Pager Dots Indicator

    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")

    implementation("com.google.dagger:hilt-android:2.48")
    annotationProcessor("com.google.dagger:hilt-compiler:2.44")

    implementation("com.squareup.retrofit2:retrofit:2.9.0") // memparsing api
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // converter
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // log data client

    implementation("com.github.bumptech.glide:glide:4.16.0") // memparsing gambar
    annotationProcessor("com.github.bumptech.glide:glide:4.16.0")

    implementation("com.google.android.material:material:1.11.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")

    // LiveData (part of Android Architecture Components)
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")

    // Firebase Library
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.android.gms:play-services-auth:20.6.0")




}