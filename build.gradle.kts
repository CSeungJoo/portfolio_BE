plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false // 빌드시 현재 모듈(multi-module)의 .jar를 생성하지 않습니다.
}

repositories {
    mavenCentral()
}


// 전역 변수로 저장
val envProperties = mutableMapOf<String, String>()

fun loadEnvFromDotEnv() {
    val envFile = rootProject.file(".env")
    if (!envFile.exists()) return

    envFile.readLines()
        .filter { it.isNotBlank() && !it.trim().startsWith("#") }
        .forEach { line ->
            val (key, value) = line.split("=", limit = 2)
            envProperties[key.trim()] = value.trim()
        }
}


loadEnvFromDotEnv()

subprojects {

    group = "kr.cseungjoo"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.getByName("annotationProcessor"))
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }


    tasks.withType<JavaExec> {
        systemProperties.putAll(envProperties)
    }

    tasks.test {
        useJUnitPlatform()
        environment(envProperties)
    }

    tasks.test {
        useJUnitPlatform()
    }
}
