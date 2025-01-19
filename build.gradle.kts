plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	war
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
	id("com.ncorti.ktfmt.gradle") version "0.21.0"
	id("org.jetbrains.kotlinx.kover") version "0.9.1"
    kotlin("kapt") version "1.9.25"
}

group = "dev.joguenco"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.rest-assured:rest-assured:5.5.0")
	// SQLite
	implementation("org.xerial:sqlite-jdbc:3.47.2.0")
	implementation("org.hibernate.orm:hibernate-community-dialects:6.6.4.Final")
	// AutoMapper Entity to DTO
	implementation("org.mapstruct:mapstruct:1.6.3")
	kapt("org.mapstruct:mapstruct-processor:1.6.3")
}

kotlin {
	compilerOptions {
//		freeCompilerArgs.addAll("-Xjsr305=strict")
		// For mapstruct
		freeCompilerArgs.addAll("-Xjvm-default=all")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

ktfmt {
	kotlinLangStyle()
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
	}
}
