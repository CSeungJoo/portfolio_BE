plugins {
	java
}

tasks.named<Jar>("jar") {
	enabled = true
}

extra["springCloudVersion"] = "2024.0.1"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-config-server")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
