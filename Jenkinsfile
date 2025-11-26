CODE_CHANGES = getGitChanges()

pipeline {
    agent any
    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Pipeline Dev') {
            steps {
                echo 'Building..'
                bat 'mvn clean'
                bat 'mvn install'
            }
        }

        stage('Image Docker') {
            steps {
                echo 'Testing..'
            }
        }

        // Staging
        stage('Start container') {
            steps {
                echo 'Cleaning up old containers and volumes...'
                bat 'docker-compose -f docker-compose.staging.yml down -v || true'

                echo 'Starting container from Docker Hub...'
                bat 'docker-compose -f docker-compose.staging.yml pull' // Baixa a imagem do Docker Hub
                bat 'docker-compose -f docker-compose.staging.yml up -d --no-color'
                // sleep time: 60, unit: 'SECONDS' // Aumenta o tempo para o serviço Spring Boot iniciar
                bat 'docker-compose -f docker-compose.staging.yml logs' // Verifica os logs para conferir o status do Spring Boot
                bat 'docker-compose -f docker-compose.staging.yml ps' // Verifica o status do container

                script {
                    echo 'Running tests against the staging container...'
                    timeout(time: 120, unit: 'SECONDS') {
                        waitUntil {
                            def exitCode = bat(script: 'curl -f http://localhost:8686', returnStatus: true)
                            return exitCode == 0
                        }
                    }
                }
                echo 'API is UP and responding!'
            }
        }

        stage('Run tests against the container') {
            steps {
                script {
                echo 'Running tests against the staging container...'
                timeout(time: 120, unit: 'SECONDS') {
                        waitUntil {
                            def exitCode = bat(script: 'curl -f http://localhost:8686', returnStatus: true)
                            return exitCode == 0
                        }
                    }
                }
                echo 'API is UP and responding!'
            }
        }
    }

    post {   
        always {
            echo 'Pipeline completed'
        }
        success {
            //
        }
        failure {
            echo 'Pipeline failed. Showing database logs:'
            bat 'docker-compose -f docker-compose.staging.yml logs database'
        }
    }
}