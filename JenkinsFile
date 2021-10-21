pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_3'
        jdk 'JDK_1_8_0'
    }
    stages {
        stage ('Compile Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage ('Testing Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn test'
                }
            }
        }
        stage ('package Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_3') {
                    bat 'mvn package'
                }
            }
        }
    }
}
