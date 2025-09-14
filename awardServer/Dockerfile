ARG FILE_DIRECTORY

FROM gradle:8.10.1-jdk17 AS builder
WORKDIR /app

COPY $FILE_DIRECTORY /app

# 실제 jar 빌드
RUN gradle :awardServer:clean :awardServer:build -x test --no-daemon

# 2단계: 실행
FROM eclipse-temurin:17-jre
WORKDIR /app

# 1단계에서 빌드된 jar 복사
COPY --from=builder /app/awardServer/build/libs/*.jar award-service.jar

# Config 서버 주소 및 Spring Profile
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "award-service.jar"]
