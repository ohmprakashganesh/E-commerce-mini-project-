# === STAGE 1: BUILD THE APPLICATION ===
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# 1. Copy the Maven Wrapper files and pom.xml
COPY pom.xml .
# --- NEW LINE ADDED HERE ---
COPY .mvn .mvn
COPY mvnw .

# 2. Copy the source code
COPY src /app/src

# 3. Make the wrapper executable (crucial for Linux/Docker)
RUN chmod +x mvnw

# 4. Build the application
# Use the full path for the wrapper to be safe, or just ./mvnw
RUN ./mvnw clean package -DskipTests


# === STAGE 2: CREATE THE FINAL IMAGE (Smaller JRE only) ===
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the JAR from the 'builder' stage
# Make sure to use the correct JAR name (from your first image/logs)
COPY --from=builder /app/target/e-commerce-crud-0.0.1-SNAPSHOT.jar app-v1.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","app-v1.jar"]