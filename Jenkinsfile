pipeline {
    agent { label 'NODE-1' }
    triggers { cron('0 5 * * *') }
    stages {
        
        stage('merge') {
            steps {
                sh '''git clone https://github.com/gopivurata/shopizer.git
                     cd shopizer           
                     git checkout relese
                     git merge origin/develop --no-ff
                     git add .
                     git commit -m "merge develop branch"
                     git push -u origin relese'''
            }
        }
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