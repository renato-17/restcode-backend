pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_3'
        jdk 'JAVA_1_8'
    }
    options {
        datadog(tags: ['team:backend'])
    }
    stages {
        stage('Integration Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage('Testing Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn test'
                }
            }
        }
        stage('Delivery Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn package'
                    bat 'mvn clean install'
                }
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                bat 'copy "target\\restcode-1.0.war" "C:\\Program Files\\jenkins\\apache-tomcat-9.0.54\\webapps"'
            }
        }
    }
}
