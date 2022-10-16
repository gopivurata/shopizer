pipeline {
    agent { label 'NODE-1' }
     triggers { cron('0 5 * * *') }
    stages {
        stage('vcs') {
           steps {
               git branch: 'relese',
               url: 'https://github.com/gopivurata/shopizer.git'
            }
        }
        stage('merge') {
            steps {
                   sh 'git checkout relese'
                   sh 'git merge origin/develop --no-ff'
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