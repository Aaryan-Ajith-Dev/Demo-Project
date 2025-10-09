FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/local/lib/app
# Copy the JAR that Jenkins already built
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
