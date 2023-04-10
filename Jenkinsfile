pipeline {
    agent any

    tools {
        maven "M2_HOME"
    }

    stages {
        stage('Version Control') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/nolancon/CarApp.git'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Code Analysis') {
            steps {
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.login=admin -Dsonar.password=root'
            }
        }
        stage('Test') {
            steps {
                sh "nohup java -jar /var/lib/jenkins/workspace/CarrAppMaven/target/SampleCarApp-0.0.1-SNAPSHOT.jar &"
                
                sh "mvn test"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
