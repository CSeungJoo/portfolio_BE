FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY build/libs/*.jar award-service.jar

# Config 서버 주소 및 Spring Profile
ENV SPRING_PROFILES_ACTIVE=prod

# 포트는 Config 서버에서 결정 → 여기선 EXPOSE 제거 가능
ENTRYPOINT ["java", "-jar", "award-service.jar"]
