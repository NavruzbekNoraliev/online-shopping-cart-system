#FROM openjdk:17-jdk-slim
#
#COPY target/reports-0.0.1-SNAPSHOT.jar /app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM maven:3.6.3-openjdk-17-slim AS MAVEN_TOOL_CHAIN
#EXPOSE 9595
WORKDIR /reports-module
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run