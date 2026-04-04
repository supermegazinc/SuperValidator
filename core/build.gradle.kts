import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.compiler)
	id("maven-publish")
}

android {
	namespace = "com.supermegazinc.validator.core"

	defaultConfig {
		minSdk = 21
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	compileSdk = 36
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	buildFeatures {
		compose = true
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlin {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}
	publishing {
		singleVariant("release") {
			withSourcesJar()
		}
	}
}

publishing {
	publications {
		register<MavenPublication>("release") {
			afterEvaluate {
				groupId = "com.supermegazinc"
				artifactId = "supervalidator"
				from(components["release"])
			}
		}
	}
}

dependencies {
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