pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_3'
        jdk 'JAVA_1_8'
    }
    options {
        datadog(tags: ["team:backend"])
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
