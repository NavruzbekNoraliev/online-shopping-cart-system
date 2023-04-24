FROM maven:3.6.3-openjdk-17-slim AS MAVEN_TOOL_CHAIN
WORKDIR /admin-module
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run