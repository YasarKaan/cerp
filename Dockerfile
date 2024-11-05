# Stage 1: Maven build with Java
FROM maven:3.8.2-openjdk-17 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: OpenJDK run
FROM openjdk:21-oracle

WORKDIR /app

LABEL authors="odinesw"

COPY --from=build /app/target/tcell_manos-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["java","-jar","backend.jar"]