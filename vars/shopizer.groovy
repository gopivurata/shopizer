def call(){
    pipeline {
    agent { label 'NODE' }
    triggers { cron('30 5 * * *') }
    stages {
        stage('vcs') {
           steps {
               git branch: 'relese',
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
}