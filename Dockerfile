# Stage 1: Build the native image using a GraalVM environment
FROM ghcr.io/graalvm/native-image:ol8-java17-22.3.0 AS build
WORKDIR /app
COPY . .
RUN ./gradlew nativeCompile --info

# Stage 2: Create a minimal runtime image
FROM alpine:latest 
RUN apk add --no-cache libc6-compat
WORKDIR /app
COPY --from=build /app/build/native/nativeCompile/helloworld .
RUN chmod +x helloworld
RUN chown 1000:1000 helloworld
USER 1000
ENTRYPOINT ["./helloworld"]
