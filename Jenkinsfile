node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/nolancon/CarApp.git'
  }

  stage("Compilation") {
    mvn clean install -DskipTests
  }

  stage("Tests and Deployment") {
    
    stage("Deployment") {
      nohup mvn 'spring-boot:run' -Dserver.port=8001 &
    }
    stage("Runing unit tests") {
      mvn test
    }
  }
}