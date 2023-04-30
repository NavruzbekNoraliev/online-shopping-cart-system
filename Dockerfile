FROM maven:3.6.3-openjdk-17-slim AS MAVEN_TOOL_CHAIN
EXPOSE 8080
WORKDIR /api-gateway
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run