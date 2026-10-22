# Use a lightweight JRE runtime container directly
FROM eclipse-temurin:21-jre-alpine

# Set the operational directory inside the container
WORKDIR /app

# Copy the pre-compiled JAR file built by your local machine directly into the container context
COPY build/libs/*.jar app.jar

# Expose the internal Spring Boot communication port
EXPOSE 8083

# Configure resource boundary allocations for microservice JVM performance
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
