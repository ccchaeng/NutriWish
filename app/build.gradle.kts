plugins {
    id("com.android.application")
    id("com.google.gms.google-services")  // Google 서비스 플러그인
}

android {
    namespace = "com.example.nutriwish"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nutriwish"
        minSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.google.android.material:material:1.12.0")
    implementation ("androidx.fragment:fragment:1.5.7")
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))  // Firebase BoM
    implementation("com.google.firebase:firebase-analytics")              // Firebase Analytics
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore")
}
