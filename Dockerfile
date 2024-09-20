# Stage 1: Build the native image using a GraalVM environment
FROM ghcr.io/graalvm/native-image:ol8-java17-22.3.0 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle files
COPY build.gradle.kts ./

# Copy Gradle wrapper (if using it)
COPY gradle/wrapper ./gradle/wrapper

# Copy the source code
COPY src ./src

# Build the native image
RUN ./gradlew nativeImage 

# Stage 2: Create a minimal runtime image
FROM alpine:latest 

# Create the app directory
WORKDIR /app

# Copy the native executable from the build stage
COPY --from=build /app/build/native/nativeCompile/helloworld .

# Set execute permissions for the binary
RUN chmod +x helloworld

# Set the entrypoint to execute the native image
ENTRYPOINT ["./helloworld"]
