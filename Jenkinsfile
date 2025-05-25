pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')
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

        stage("SonarQube Analysis") {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=kaddem -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
    }
}