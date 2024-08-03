plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.mermake.locgen)
}

android {
    namespace = "com.example.locgentester"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.locgentester"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        resourceConfigurations.addAll(
            listOf(
//                "af", "af-rZA",
//                "am", "am-rET",
//                "ar", "ar-rEG", "ar-rIL",
//                "as",
//                "az",
//                "be",
//                "bg", "bg-rBG",
//                "bn",
//                "bs",
//                "ca", "ca-rES",
//                "cs", "cs-rCZ",
//                "da", "da-rDK",
//                "de", "de-rAT", "de-rCH", "de-rDE", "de-rLI",
//                "el", "el-rGR",
//                "en-rAU", "en-rCA", "en-rGB", "en-rIE", "en-rIN", "en-rNZ", "en-rSG", "en-rUS", "en-rZA",
//                "es", "es-rCO", "es-rCR", "es-rEC", "es-rES", "es-rGT", "es-rHN", "es-rMX", "es-rNI", "es-rPA", "es-rPE", "es-rSV", "es-rUS",
//                "et",
//                "eu",
//                "fa", "fa-rIR",
//                "fi", "fi-rFI",
//                "fr", "fr-rBE", "fr-rCA", "fr-rCH", "fr-rFR",
//                "gl",
//                "gu",
//                "he",
//                "hi", "hi-rIN",
//                "hr", "hr-rHR",
//                "hu", "hu-rHU",
//                "hy",
//                "in", "in-rID",
//                "is",
//                "it", "it-rCH", "it-rIT",
//                "iw", "iw-rIL",
//                "ja", "ja-rJP",
//                "ka",
//                "kk",
//                "km",
//                "kn",
//                "ko", "ko-rKR",
//                "ky",
//                "lo",
//                "lt", "lt-rLT",
//                "lv", "lv-rLV",
//                "sr-Latn",
//                "zh", "zh-rCN", "zh-rTW",
                "en",

                // pseudolocales
                "en-rXA",
                "ar-rXB",
            )
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isPseudoLocalesEnabled = true
        }
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