pipeline {
  agent any
  tools {
    maven 'maven 3.5.0'
    jdk 'Java8'
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn -f auditor-core/pom.xml clean install'
      }
      post {
        always {
          archive '**/target/surefire-reports/*.xml'
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Publish artifact') {
      steps {
        sh 'mvn -f auditor-core/pom.xml deploy'
      }
    }

    stage('Sonar Update') {
      steps {
        sh 'mvn -f auditor-core/pom.xml sonar:sonar -Dsonar.host.url=${URL_SONAR} -Dsonar.password=${PWD_SONAR} -Dsonar.login=${USER_SONAR}'
      }
    }
    }
  }
