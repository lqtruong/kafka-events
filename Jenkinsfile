pipeline {
    options {timeout(time: "${BUILD_TIMEOUT_MINUTES}", unit: 'MINUTES')}
    environment {
        JAVA_TOOL_OPTIONS = '-Duser.home=/var/maven'
        SONAR_HOST_URL = 'localhost:9000'
    }
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'OpenJDK11'
        //docker
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        /*stage('Tools check') {
            steps {
                sh 'mvn --version'
                sh 'java --version'
                //sh 'docker --version'
            }
        }*/
        stage('Compile & Test') {
            steps {
                sh 'mvn clean compile test'
            }
            post {
                always {
                    junit 'order-service/target/surefire-reports/*.xml'
                }
            }
        }
        stage('SonarQube analysis') {
            when {
                branch 'develop'
            }
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'printenv'
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }
    }
}
