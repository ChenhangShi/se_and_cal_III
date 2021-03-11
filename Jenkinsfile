pipeline {
    agent any

    stages {
        stage('build project'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('run'){
            when{
                branch 'release'
            }
            steps{
                sh '''sh stop.sh
sh start.sh'''
            }
        }
    }
}
