# Stage 1: Build
FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# Stage 2: Run
FROM eclipse-temurin:17.0.9_9-jre-ubi9-minimal
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
USER 1000
CMD ["java", "-jar", "app.jar"]
