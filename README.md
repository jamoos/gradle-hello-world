## Gradle Hello World Project with CI/CD Pipeline

This repository demonstrates a basic Gradle project that produces a "Hello World" JAR file. Additionally, it includes a GitHub Actions CI/CD pipeline to automate the build, versioning, and Docker image creation process.

### Project Structure

*   **`src/main/java/com/ido/HelloWorld.java`**: Contains the main Java class that prints a greeting along with the application's version number.
*   **`build.gradle.kts`**: Defines the Gradle build configuration, including dependencies, tasks, and the project version.
*   **`.github/workflows/pipeline.yml`**: Defines the GitHub Actions workflow for CI/CD.

### CI/CD Pipeline

The pipeline consists of three main jobs:

1.  **`bump-version`**: 
    *   Automatically increments the patch version in `build.gradle.kts`.
    *   Uploads the updated `build.gradle.kts` as an artifact.

2.  **`build-artifact`**:
    *   Downloads the updated `build.gradle.kts` artifact.
    *   Builds the JAR file using Gradle.
    *   Uploads the JAR as an artifact to GitHub Actions, named with the version number.

3.  **`build-docker`**:
    *   Downloads the updated `build.gradle.kts` artifact.
    *   Builds a tiny Docker image using a multi-stage Dockerfile - leverages Graalvm native image and alpine.
    *   Tags the image with both `latest` and the specific version number.
    *   Pushes the image to Docker Hub.

4.  **`test-docker`**
    *   Pulls the newly built Docker image from Docker Hub.
    *   Runs the container and verifies that the output matches the expected message
5.  **`commit-new-version`**
    *   Commits the version bump in `build.gradle.kts` to the `master` branch only if all previous jobs, including `test-docker`, have succeeded

### Key Features

*   **Automated Versioning:** Ensures consistent and traceable version increments with each push to the `master` branch.
*   **JAR Artifact:** Provides a readily available JAR file for deployment or other purposes.
*   **Docker Image:** Creates a containerized version of the application for easy deployment and execution in various environments.
*   **Non-Root User:** The Docker image is configured to run as a non-root user for improved security.
*   **Automated Testing:** Verifies the correct behavior of the Dockerized application by checking its output.
*   **Conditional Commit:** The version bump is committed only after successful testing, ensuring code quality.

### How to Run

### How to Run

1.  **Clone the repository:** `git clone https://github.com/<your-username>/gradle-hello-world.git`
2.  **Build the project locally:** `./gradlew build`
3.  **Run the JAR:** `java -jar build/libs/helloworld-<version>.jar`
4.  **Trigger the CI/CD pipeline:** Push any changes to the `master` branch.
5.  **Pull and run the Docker image:** 
    *   `docker pull jamoos/graal:latest` or `docker pull jamoos/graal:<version>`
    *   `docker run jamoos/graal:latest` or `docker run jamoos/graal:<version>`

**Note:** Make sure you have Docker installed and have set up your Docker Hub credentials as secrets in your GitHub repository settings.

