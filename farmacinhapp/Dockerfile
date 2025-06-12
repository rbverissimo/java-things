FROM openjdk:11-jdk-slim AS build-env

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -B

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build-env /app/mvnw .
COPY --from=build-env /app/mvn ./mvn
COPY --from=build-env /root/.m2 /root/.m2

EXPOSE 8080
