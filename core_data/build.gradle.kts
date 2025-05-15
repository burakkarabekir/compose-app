import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
}

fun getProperties(fileName: String): Properties {
    val properties = Properties()
    val propertiesFile = rootProject.file(fileName)
    if (propertiesFile.exists()) {
        properties.load(propertiesFile.inputStream())
    }
    return properties
}

val configProperties = getProperties("config.properties")

android {
    namespace = "com.bksd.core_data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", configProperties.getProperty("DEBUG_BASE_URL"))
            buildConfigField("String", "KEY_API_KEY", configProperties.getProperty("KEY_API_KEY"))
            buildConfigField(
                "String",
                "VALUE_API_KEY",
                configProperties.getProperty("VALUE_API_KEY")
            )
            buildConfigField("String", "KEY_API_HOST", configProperties.getProperty("KEY_API_HOST"))
            buildConfigField(
                "String",
                "VALUE_API_HOST",
                configProperties.getProperty("VALUE_API_HOST")
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", configProperties.getProperty("RELEASE_BASE_URL"))
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core_domain"))

    implementation(libs.androidx.core.ktx)

    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.logging)
    // Optional -- Mockito framework
    testImplementation(libs.mockito.core)
    // Optional -- mockito-kotlin
    testImplementation(libs.mockito.kotlin)
    // Optional -- Mockk framework
    testImplementation(libs.mockk)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Koin
    implementation(libs.koin.android)

    // Room
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(kotlin("test"))
}