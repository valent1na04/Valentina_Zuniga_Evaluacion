FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -DskipTests dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd -ms /bin/bash appuser
COPY --from=build /app/target/*.jar /app/backend.jar
RUN chown -R appuser:appuser /app
RUN chmod a+rx /app/backend.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]