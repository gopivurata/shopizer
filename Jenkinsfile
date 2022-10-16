pipeline {
    agent { label 'NODE-1' }
    triggers { pollSCM('* * * * *') }
    stages {
        stage('vcs') {
           steps {
               git branch: 'develop',
               url: 'https://github.com/gopivurata/shopizer.git'
            }
        }
        stage('build') {
            steps {
                sh '/usr/share/maven/bin/mvn package'
            }
        }
        stage('archive results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar'
            }
        }
    }
}