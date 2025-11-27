pipeline {
    agent any
    
    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Se marcado, executa os testes durante o build')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('[Pipeline Dev] Build (Maven) e Testes') {
            steps {
                echo 'Building Application...'
                script {
                    // Roda o Maven e gera os relatórios (Surefire, PMD, JaCoCo)
                    def mavenCmd = 'mvn clean install pmd:pmd'
                    if (params.executeTests == false) {
                        mavenCmd += ' -DskipTests'
                    }
                    bat mavenCmd
                }
            }

            post {
                always {
                    // 1. Publicar testes JUnit
                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true

                    // 2. Publicar PMD
                    recordIssues(
                        enabledForFailure: true,
                        tools: [mavenConsole(), pmdParser(pattern: '**/target/pmd.xml')]
                    )

                    // 3. Quality Gate de Cobertura 
                    recordCoverage(
                        tools: [[parser: 'JACOCO', pattern: '**/target/site/jacoco/jacoco.xml']],
                        sourceCodeRetention: 'LAST_BUILD',
                        qualityGates: [
                            [
                                threshold: 97.0, 
                                metric: 'LINE', 
                                baseline: 'PROJECT', 
                                // De UNSTABLE para FAILURE
                                // Isso faz o pipeline PARAR aqui se não atingir o threshold
                                criticality: 'FAILURE' 
                            ]
                        ]
                    )
                }
            }
        }

        stage('Image Docker') {
            steps {
                echo 'Construindo Imagem Docker...'
                
                
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
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed. Showing database logs for debugging:'
            bat 'docker-compose -f docker-compose.staging.yml logs database'
        }
    }
}