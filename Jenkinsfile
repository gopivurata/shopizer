pipeline {
    agent { label 'NODE-1' }
    stages {
        stage('vcs') {
           steps {
               git branch: 'relese',
               url: 'https://github.com/gopivurata/shopizer.git'
            }
        }
        stage('merge') {
            steps {
                     git checkout relese
                     git merge develop --no-ff
            }
        }