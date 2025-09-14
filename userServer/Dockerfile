FROM gradle:8.10.1-jdk17 AS builder
WORKDIR /app

# 의존성 캐시 활용
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

RUN gradle build -x test --continue || true

# 소스 코드 복사
COPY src ./src

# 실제 jar 빌드
RUN gradle clean build -x test

# 2단계: 실행
FROM eclipse-temurin:17-jre
WORKDIR /app

# 1단계에서 빌드된 jar 복사
COPY --from=builder /app/build/libs/*.jar user-service.jar

# Config 서버 주소 및 Spring Profile
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "user-service.jar"]
