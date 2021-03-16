def BUILD_TIMEOUT = 5
pipeline {
    options {timeout(time: "${BUILD_TIMEOUT}", unit: 'MINUTES')}
    environment {
        JAVA_TOOL_OPTIONS = '-Duser.home=/var/maven'
    }
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'OpenJDK11'
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Tools check') {
            steps {
                sh 'mvn --version'
                sh 'java --version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}