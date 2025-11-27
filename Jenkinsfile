pipeline {
    agent any
    
    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Se marcado, executa os testes')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Pipeline Dev') {
            steps {
                echo 'Building Application...'
                // Garante que o Maven use o Java correto se configurado no Global Tools
                // Se estiver usando o do sistema, o 'bat' direto funciona
                bat 'mvn clean install'
            }
        }

        stage('Image Docker') {
            steps {
                echo 'Placeholder for Docker Build...'
                // Aqui você colocaria o 'docker build' futuramente
            }
        }

        // --- Início do Staging ---
        stage('Start Staging Environment') {
            steps {
                echo 'Cleaning up old containers and volumes...'
                // O '|| true' impede falha se não houver nada para derrubar
                bat 'docker-compose -f docker-compose.staging.yml down -v || true'

                echo 'Starting container from Docker Hub...'
                bat 'docker-compose -f docker-compose.staging.yml pull'
                bat 'docker-compose -f docker-compose.staging.yml up -d --no-color'
                
                // Mostra o status inicial
                bat 'docker-compose -f docker-compose.staging.yml ps'
            }
        }

        stage('Verify Staging Health') {
            steps {
                script {
                    echo 'Waiting for API to be ready...'
                    // Tenta verificar a API por até 2 minutos
                    timeout(time: 120, unit: 'SECONDS') {
                        waitUntil {
                            // O 'returnStatus: true' impede que o pipeline falhe se o curl der erro
                            // O curl -f falha se o HTTP code não for 200 OK
                            def exitCode = bat(script: 'curl -f http://localhost:8686', returnStatus: true)
                            
                            // Se exitCode for 0 (sucesso), o waitUntil para. Se não, ele tenta de novo.
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
        failure {
            echo 'Pipeline failed. Showing database logs for debugging:'
            bat 'docker-compose -f docker-compose.staging.yml logs database'
        }
    }
}