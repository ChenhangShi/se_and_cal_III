pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/release']], extensions: [], userRemoteConfigs: [[credentialsId: 'd36fb73b-4ab2-4e62-9bb2-15cd759d6f0b', url: 'http://212.129.149.40/181250172_codemonkeys/backend-coin.git']]])
            }
        }
        stage('build project'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('jacoco report'){
            steps{
                jacoco classPattern: '**/target/classes', execPattern: '**/target/**.exec'
            }
        }
        stage('run'){
            steps{
                withEnv(['JENKINS_NODE_COOKIE=dontkillme']){
                    sh 'nohup java -jar /var/lib/jenkins/workspace/backend-coin/target/backend-coin-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod &'
                }
            }
        }
    }
}
