pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')       // Optionnel pour plus tard
        DOCKER_USER = credentials('dockerhub-user')    // Optionnel pour plus tard
        DOCKER_PASS = credentials('dockerhub-pass')    // Optionnel pour plus tard
    }

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
