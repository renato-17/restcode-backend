pipeline {
    agent any
    tools {
        tomcatWeb 'C:\\Program Files\\jenkins\\apache-tomcat-9.0.54\\webapps'
        tomcatBin 'C:\\Program Files\\jenkins\\apache-tomcat-9.0.54\\bin'
        maven 'MAVEN_3_8_3'
        jdk 'JAVA_1_8'
    }
    options {
        datadog(tags: ['team:backend'])
    }
    stages {
        stage('Integration Stage') {
            steps {
                withMaven(maven : maven) {
                    bat 'mvn clean compile'
                }
            }
        }
        stage('Testing Stage') {
            steps {
                withMaven(maven : maven) {
                    bat 'mvn test'
                }
            }
        }
        stage('Delivery Stage') {
            steps {
                withMaven(maven : maven) {
                    bat 'mvn package'
                    bat 'clean install'
                }
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                bat "copy target\\restcode-backend.war \"${tomcatWeb}\\restcode-backend.war\""
            }
        }
        stage('Start Tomact Server') {
            steps {
                sleep(time:5, unit:'SECONDS')
                bat "${tomcatBin}\\startup.bat"
            }
        }
    }
}
