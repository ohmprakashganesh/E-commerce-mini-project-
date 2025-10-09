FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/e-commerce-crud-0.0.1-SNAPSHOT.jar app-v1.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app-v1.jar"]

