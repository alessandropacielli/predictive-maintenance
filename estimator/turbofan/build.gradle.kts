import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
}

group = "com.alessandropacielli"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "Hoxton.SR3"

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-stream")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
	implementation("org.deeplearning4j:deeplearning4j-core:1.0.0-beta6")
	implementation("org.nd4j:nd4j-native-platform:1.0.0-beta6")
	implementation("org.influxdb:influxdb-java:2.17")
	implementation("uk.co.jemos.podam:podam:7.2.3.RELEASE")
	implementation("com.squareup.okio:okio:1.17.5")
	implementation("com.squareup.okhttp3:okhttp:3.14.4")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
