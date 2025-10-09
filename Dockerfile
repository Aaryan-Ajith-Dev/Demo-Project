# Start from a minimal Java Runtime Environment (JRE) image.
# This image is tiny and does NOT contain any build tools (like Maven), which increases security.
FROM eclipse-temurin:21-jre-alpine

# Set the final working directory
WORKDIR /usr/local/lib/app

# Copy the final compiled artifact (the JAR file) from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Define the default command to run when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]