# Plataforma de Cursos - Projeto DevOps & Clean Architecture
Este é um projeto de API REST para gestão de cursos e alunos, desenvolvido com foco em boas práticas de Engenharia de Software (**Clean Architecture**, **DDD**) e **DevOps**. O projeto inclui um pipeline completo de CI/CD com verificações de qualidade de código e deploy automatizado em ambiente de staging via Docker.

## 🚀 Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3.2.5** (Web, Data JPA)
* **Banco de Dados:**
    * **H2 Database** (Ambiente de Desenvolvimento/Testes)
    * **PostgreSQL** (Ambiente de Staging/Docker)
* **Lombok**
* **SpringDoc OpenAPI** (Swagger UI)
* **Docker & Docker Compose**

## 🧪 Qualidade e Testes

O projeto possui uma forte cultura de testes automatizados configurados no Maven:

* **Testes Unitários e de Integração:** JUnit 5 e Mockito.
* **Cobertura de Código:** **JaCoCo** configurado com Quality Gate de **97%** (a meta é chegar aos 99%).
* **Análise Estática:** **PMD** para detecção de más práticas e bugs potenciais.

## 🔄 Pipeline de CI/CD (Jenkins)

O arquivo `Jenkinsfile` define o pipeline de entrega contínua:

1.  **Checkout:** Baixa o código fonte.
2.  **Build & Test:** Compila o projeto, roda testes unitários e gera relatórios de PMD e JaCoCo.
    * *Quality Gate:* O pipeline falha se a cobertura de testes for menor que 97%.
3.  **Docker Build & Push:** Constrói a imagem Docker e envia para o Docker Hub.
4.  **Deploy Staging:**
    * Limpa volumes antigos (`docker-compose down -v`).
    * Sobe os containers (`app` e `postgres`).
    * Aguardar a saúde do banco de dados (`healthcheck` nativo).
5.  **Verify Health:** Executa um teste de fumaça (`curl`) aguardando a API responder `HTTP 200`.

## 📝 User Story e BDDs
**User Story Escolhida:** COMO aluno QUERO poder visualizar a lista de cursos disponíveis PARA que eu possa escolher qual curso assinar.
<br>
<br>
**Integrante: Guilherme**<br>
DADO que sou um aluno<br>
QUANDO acesso a página principal da plataforma<br>
ENTÃO devo visualizar a lista de cursos disponíveis<br>
<br>
**Integrante: Gustavo**<br>
DADO que eu sou um aluno autenticado no sistema<br>
E que não existem cursos publicados na plataforma<br>
QUANDO eu acesso a página de "Cursos"<br>
ENTÃO eu devo ver uma mensagem informativa como "Nenhum curso disponível no momento. Volte em breve!"
<br><br>
**Integrante: Giovana**<br>
DADO que estou visualizando a lista de cursos disponíveis<br>
E vejo um curso chamado "Gamificação Aplicada à Educação"<br>
QUANDO eu clicar neste curso<br>
ENTÃO devo ser redirecionado para a página de detalhes do curso "Gamificação Aplicada à Educação"<br>
E nesta página devo visualizar um botão de "Assinar Curso"<br>
<br>

**Integrante: João**<br>
DADO que estou na página de "Cursos"<br>
E que existem cursos das categorias "Tecnologia", "Finanças" e "Marketing" publicados<br>
QUANDO eu selecionar a categoria "Tecnologia"<br>
ENTÃO devo visualizar a lista de cursos da categoria "Tecnologia"<br>
E não devo visualizar nenhum curso das categorias "Finanças" e "Marketing"<br>
<br>
**Integrante: Armando**<br>
DADO que o aluno está autenticado na plataforma<br>
E existem cursos disponíveis com informações completas (nome, descrição, carga horária, preço e instrutor)<br> 
QUANDO o aluno acessa a página de cursos disponíveis<br>
E seleciona um curso específico<br>
ENTÃO ele deve ser redirecionado para a página de detalhes do curso<br>
E deve visualizar todas as informações do curso<br>
E ter a opção de assinar o curso<br>
