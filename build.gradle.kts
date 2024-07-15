plugins {
    id("java")
    id("org.springframework.boot") version "3.2.7"
    id("io.spring.dependency-management") version "1.1.3"
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false // 빌드시 현재 모듈(multi-module)의 .jar를 생성하지 않습니다.
}

repositories {
    mavenCentral()
}

subprojects {
    group = "com.example"
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
        implementation("org.springframework.boot:spring-boot-starter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.test {
        useJUnitPlatform()
    }
}
