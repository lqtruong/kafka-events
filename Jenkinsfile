def BUILD_TIMEOUT = 5
pipeline {
    options {timeout(time: "${BUILD_TIMEOUT}", unit: 'MINUTES')}
    environment {
        JAVA_TOOL_OPTIONS = '-Duser.home=/var/maven'
    }
    agent any
    /* agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME:/var/maven -v /var/run/docker.sock:/var/run/docker.sock --group-add 117 --network="host"'
            reuseNode true
        }
    } */
    triggers {
        pollSCM '* * * * *'
    }
    stages {
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