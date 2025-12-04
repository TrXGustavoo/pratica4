pipeline {
    agent any
    
    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Se marcado, executa os testes durante o build')
    }

    environment {
        DOCKER_IMAGE = 'gimerguizo/ac2_ca' 
        DOCKER_CREDENTIALS_ID = 'docker_jenkins'
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
                                threshold: 97, 
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

        stage('Build & Publish Docker Image') {
            steps {
                echo 'Construindo e Publicando Imagem Docker...'
                // Chamar alvos Maven de alto nível
                bat 'mvn clean package -Dmaven.test.skip=true'

                // Login no Docker Hub usando as credenciais do Jenkins
                withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    
                    // Login 
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                    
                    // Build da imagem
                    // Assume que o Dockerfile está na raiz. Ajuste o caminho se necessário.
                    bat "docker build -t ${DOCKER_IMAGE}:latest ."
                    
                    // Push para o Docker Hub
                    bat "docker push ${DOCKER_IMAGE}:latest"
                    
                    echo 'Imagem publicada com sucesso!'
                    
                    // Logout por segurança
                    bat 'docker logout'
                }
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

        stage('Notificação Discord') {
            steps {
                script {
                    def d = [:]
                    def props = readProperties(defaults: d, file: './discord-id.properties')
                    env.ID1 = props['id_Giovana']
                    env.TIME = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East")).getTime().format('dd/MM/YYYY - hh:mm:ss')
                }
                discordSend description: "Notificação",
                notes: "Executado por: <@${env.ID1}>",
                footer: "Execução: ${env.TIME}",
                link: env.BUILD_URL,
                result: currentBuild.currentResult,
                title: JOB_NAME,
                webhookURL: "https://discord.com/api/webhooks/1446233801448624189/2d0Z04p8ipbXXQJllw3LjJleXpiETucY3APaTMTmzwSQ7QDbW6R9Gj3HFPH7E90sRE8x"
                enableArtifactsList: false
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