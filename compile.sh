#!/bin/bash
mvn clean install -DskipTest

nohup mvn spring-boot\:run -Dserver.port=8081

mvn test