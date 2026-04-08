import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.supermegazinc.validator.examples.compose_form"
	compileSdk {
		version = release(36)
	}

	defaultConfig {
		minSdk = 21
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	buildFeatures {
		compose = true
	}
	buildTypes {
		release {
			isMinifyEnabled = false
		}
	}
	kotlin {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {
	implementation(project(":core"))
	implementation(libs.androidx.core.ktx)
	testImplementation(libs.junit)
	api(libs.superflow)
	val composeBom = platform("androidx.compose:compose-bom:2026.03.00")
	implementation(composeBom)
	androidTestImplementation(composeBom)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.ui.tooling.preview)
	debugImplementation(libs.androidx.ui.tooling)
	testImplementation(kotlin("test"))
	implementation(kotlin("reflect"))
}