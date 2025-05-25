pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
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
                sh 'mvn clean verify'
            }
        }

        stage("SonarQube Analysis") {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=kaddem -Dsonar.login=$SONAR_TOKEN -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }
        stage("Deploy artifact to Nexus") {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }
        stage("Build Docker Image") {
            steps {
                sh 'docker build -t mahdihemriti/eventsproject:latest .'
            }
        }
        stage("Push Docker Image to DockerHub") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                      echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                      docker push mahdihemriti/eventsproject:latest
                    """
                }
            }
            }
            stage("Run Docker Compose") {
                steps {
                                    sh 'docker-compose down || true'
                                    sh 'docker-compose up -d'
                }
            }

    }
}