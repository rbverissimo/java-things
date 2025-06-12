FROM openjdk:11-jdk-slim AS builder
LABEL maintainer="Renato Verissimo <coltran.solucoes@gmail.com>"

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn/
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:11-jre-slim AS final

COPY --from=builder /app/target/*.jar app.jar

USER nobody

CMD ["java", "-jar", "app.jar"]