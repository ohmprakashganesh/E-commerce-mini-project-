# === STAGE 1: BUILD THE APPLICATION ===
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy the build files (pom.xml, src, etc.)
COPY pom.xml .
COPY src /app/src

# Build the application
RUN ./mvnw clean package -DskipTests

# === STAGE 2: CREATE THE FINAL IMAGE (Smaller JRE only) ===
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the JAR from the 'builder' stage
COPY --from=builder /app/target/e-commerce-crud-0.0.1-SNAPSHOT.jar app-v1.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","app-v1.jar"]