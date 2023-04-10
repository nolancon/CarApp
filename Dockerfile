FROM openjdk:8-jdk-alpine

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} ./CarApp.jar
ENTRYPOINT ["java", "-jar", "./CarApp.jar"]
