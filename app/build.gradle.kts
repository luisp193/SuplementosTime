plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.suplementostime.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.suplementostime.app"
        minSdk = 24
        targetSdk = 34
        // Para sacar una versión nueva solo hay que subir este número (V5, V6...).
        // El APK sale con el nombre SuplementosTime_V<versionCode>.apk
        versionCode = 4
        versionName = "V4"
    }
    // Clave fija: así cada APK compilado en GitHub tiene la misma firma y se puede actualizar encima.
    signingConfigs {
        create("fija") {
            storeFile = file("signing.p12")
            storeType = "pkcs12"
            storePassword = "suplementostime"
            keyAlias = "suplementostime"
            keyPassword = "suplementostime"
        }
    }
    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("fija")
        }
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
}

dependencies {
    val bom = platform("androidx.compose:compose-bom:2024.09.00")
    implementation(bom)
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-core")
    testImplementation("junit:junit:4.13.2")
}
