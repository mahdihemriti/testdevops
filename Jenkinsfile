pipeline {
    agent any

    stages {
        stage("Git Checkout") {
            steps {
                git branch: 'master',
                    url: 'https://github.com/mahdihemriti/testdevops.git',
                    credentialsId: 'github-credentials'
            }
        }

        stage("Build Project") {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage("Run Unit Tests") {
            steps {
                sh 'mvn test'
            }
        }
    }
}
