# Build stage: compile and package the jar with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B package --file pom.xml -DskipTests

# Runtime stage: only the JRE and the built jar, nothing else
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/subject-verb.jar ./subject-verb.jar
ENTRYPOINT ["java", "-jar", "subject-verb.jar"]
