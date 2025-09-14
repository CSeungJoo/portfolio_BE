FROM gradle:8.10.1-jdk17 AS builder
WORKDIR /app

# 루트 프로젝트 파일들 복사 (멀티모듈 지원)
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# 공통 모듈 복사
COPY commonModule ./commonModule

# 현재 서비스 빌드 파일 복사
COPY awardServer/build.gradle.kts ./awardServer/

# 의존성 다운로드 (캐시 활용)
RUN gradle :awardServer:dependencies --no-daemon

# 소스 코드 복사
COPY awardServer/src ./awardServer/src

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
