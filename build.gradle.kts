import com.palantir.gradle.docker.DockerExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"

    kotlin("jvm") version "1.3.41"
    kotlin("kapt") version "1.3.41"
    kotlin("plugin.spring") version "1.3.41"
    kotlin ("plugin.jpa") version "1.3.41"

    id("com.palantir.docker") version "0.22.1"
    id("com.github.ben-manes.versions") version "0.28.0"

    `maven-publish`
}

group = "org.devshred"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core:5.2.4")
    implementation("org.postgresql:postgresql:42.2.6")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("org.testcontainers:junit-jupiter:1.11.3")
    testImplementation("org.testcontainers:postgresql:1.11.3")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

val bootJar: BootJar by tasks

configure<DockerExtension> {
    name = "devshred/rest-app"
    tag("latest", "devshred/rest-app:latest")
    files(bootJar.archiveFile)
    setDockerfile(file("src/main/docker/Dockerfile"))
    buildArgs(mapOf("JAR_FILE" to bootJar.archiveFileName.get()))
    dependsOn(tasks["build"])
    pull(true)
}

springBoot {
    buildInfo()
}