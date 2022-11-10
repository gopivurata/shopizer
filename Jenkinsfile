pipeline {
     agent  { label 'node' }
     stages {
        stage('git') {
            steps {
                git branch: "master", 
                url: 'https://github.com/gopivurata/shopizer.git'
            }

        }
        stage ('Artifactory configuration') {
            steps {
                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "Jfrog_config",
                    releaseRepo: 'myproject-libs-release',
                    snapshotRepo: 'myproject-libs-snapshot'
                )

            }
        }
        stage ('Exec Maven') {
            steps {
                rtMavenRun (
                    tool: 'MAVEN_DEPLOYER', // Tool name from Jenkins configuration
                    pom: 'pom.xml',
                    goals: 'install',
                    deployerId: "MAVEN_DEPLOYER"
                )
            }
        }
        stage ('publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "Jfrog_config"
                )
            }
        }
    }
}
