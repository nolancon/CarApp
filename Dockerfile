FROM openjdk:11

ARG JAR_FILE="target/*.jar"
COPY ${JAR_FILE} ./CarApp.jar
ENTRYPOINT ["java", "-jar", "./CarApp.jar"]
