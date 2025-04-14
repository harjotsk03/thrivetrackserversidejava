# Use a JDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy your jar file into the image
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
