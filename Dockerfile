
# Use an official OpenJDK runtime as the base image
FROM --platform=$BUILDPLATFORM openjdk:17-oracle as build

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container
COPY . /app

# Copy the Gradle wrapper files
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/

RUN chmod +x ./gradlew

RUN ./gradlew dependencies

# Run the Gradle build to create the Spring Boot jar file
RUN ./gradlew bootJar

FROM openjdk:17-oracle

WORKDIR /app

COPY --from=build /app ./


# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "build/libs/your-application-name.jar"]
