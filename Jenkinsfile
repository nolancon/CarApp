node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/nolancon/CarApp.git'
  }

  stage("Compilation") {
    mvn clean install -DskipTests
  }

  stage("Deploy and Test") {
    stage("Deployment") {
      nohup maven spring-boot:run -Dserver.port=8081 &
    }
    
    stage("Runing tests") {
      maven test
    }
  }
}