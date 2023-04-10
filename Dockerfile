FROM openjdk:8-jdk-alpine

ARG JAR_FILE="/var/lib/jenkins/workspace/CarAppPipeline/target/SampleCarApp-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} ./CarApp.jar
ENTRYPOINT ["java", "-jar", "./CarApp.jar"]
