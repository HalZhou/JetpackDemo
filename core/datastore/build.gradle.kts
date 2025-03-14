plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "com.example.jetpack.datastore"
}

dependencies {
    implementation(libs.androidx.dataStore.perferences)
    implementation(libs.androidx.dataStore.perferences.core)
}