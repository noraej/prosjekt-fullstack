# syntax=docker/dockerfile:1

FROM openjdk:11 as build
WORKDIR /workspace/app

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
