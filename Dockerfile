# Step 1: Build the JAR using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only the pom first (for caching dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Step 2: Run the JAR in a smaller JDK image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Automatically pick up the JAR from target
COPY --from=build /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
