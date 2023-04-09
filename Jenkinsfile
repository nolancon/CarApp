node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/nolancon/CarApp.git'
  }

  stage("Compilation") {
    sh 'maven clean install -DskipTests'
  }

  stage("Deploy and Test") {
    stage("Deployment") {
      sh 'nohup maven spring-boot:run -Dserver.port=8081 &'
    }
    
    stage("Runing tests") {
      sh 'maven test'
    }
  }
}