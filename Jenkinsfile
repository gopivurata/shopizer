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
                sh 'git clone https://github.com/gopivurata/shopizer.git'
                sh 'git checkout relese'
                sh 'git merge develop --no-ff'
                sh 'git add .'
                sh 'git commit -m "merge develop branch"'
                sh 'git push'
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