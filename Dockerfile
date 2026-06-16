# Stage 1: build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/cadastroclientes-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx384m", "-jar", "app.jar"]
