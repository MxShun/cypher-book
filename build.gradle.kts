import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("io.gitlab.arturbosch.detekt") version "1.19.0"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0" apply false
}

group = "cypher"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

detekt {
	toolVersion = "1.19.0"
	buildUponDefaultConfig = true
	allRules = false
	config = files("$projectDir/detekt/detekt.yml")
}

dependencies {
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
	jvmTarget = "11"
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.jetbrains.kotlin.plugin.spring")
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	tasks {
		withType<KotlinCompile> {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "11"
			}
		}

		withType<Test> {
			useJUnitPlatform()
		}
	}
}
