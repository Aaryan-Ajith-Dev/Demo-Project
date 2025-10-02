# -------------------- STAGE 1: BUILD THE APPLICATION --------------------
# Use a full JDK image that includes Maven/Gradle and all necessary build tools
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory inside this stage
WORKDIR /app

# Copy the dependency files (pom.xml for Maven) first to leverage Docker's cache
# If dependencies haven't changed, this layer and the next are reused.
COPY pom.xml .

# If using Maven, download dependencies (this is the key caching layer)
# RUN mvn dependency:go-offline

# Copy the entire source code
COPY src /app/src

# Run the full build command. This compiles the code and packages it into a JAR file.
# The '-DskipTests' is often used in a Docker build, assuming your CI server
# ran tests in a separate step, but you can remove it if you want tests to run here.
RUN mvn package -DskipTests

# -------------------- STAGE 2: CREATE THE FINAL, SMALL IMAGE --------------------
# Start from a minimal Java Runtime Environment (JRE) image.
# This image is tiny and does NOT contain any build tools (like Maven), which increases security.
FROM eclipse-temurin:21-jre-alpine

# Set the final working directory
WORKDIR /usr/local/lib/app

# Copy the final compiled artifact (the JAR file) from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Define the default command to run when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]