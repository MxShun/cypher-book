import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("io.gitlab.arturbosch.detekt") version "1.18.1"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "cypher"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

detekt {
	toolVersion = "1.18.1"
	buildUponDefaultConfig = true // preconfigure defaults
	allRules = false // activate all available (even unstable) rules.
	config = files("$projectDir/detekt/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging:1.12.5")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
	// Target version of the generated JVM bytecode. It is used for type resolution.
	jvmTarget = "11"
}

tasks.withType<Test> {
	useJUnitPlatform()
}
