plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

repositories {
	mavenCentral()
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

allprojects {
	group = "com.seegrid"
	version = "0.0.1-SNAPSHOT"
}

subprojects {
	apply {
		plugin("java")
		plugin("io.spring.dependency-management")
		plugin("org.springframework.boot")
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		// Various expressive annotations
		implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
		implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

		// Core Unit Testing
		testImplementation(platform("org.junit:junit-bom:5.10.0"))
		testImplementation("org.junit.jupiter:junit-jupiter")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")

		// Fluent Assertions library
		testImplementation("org.assertj:assertj-core:3.26.3")

		// Mocking support
		testImplementation("org.mockito:mockito-core:5.12.0")
		testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")

		// Google Guava Collections
		implementation("com.google.guava:guava:33.2.1-jre")

		// Spring Boot
		implementation("org.springframework.boot:spring-boot-starter-validation")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
		implementation("org.springframework.boot:spring-boot-starter-webflux")

		// Database and Migrations
		runtimeOnly("org.postgresql:postgresql")
		runtimeOnly("org.postgresql:r2dbc-postgresql")

		// Database migrations
		implementation("org.flywaydb:flyway-core")
		implementation("org.flywaydb:flyway-database-postgresql")

		// Mapping Data Models
		implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")

		// Lombok
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}
}


