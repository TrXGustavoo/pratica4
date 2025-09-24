# Prática 4 e 6

Repositório utilizado para as Práticas 4 e 6 da disciplina de DevOps.

## User Story e BDDs
User Story Escolhida: COMO aluno QUERO poder visualizar a lista de cursos disponíveis PARA que eu possa escolher qual curso assinar.
<br>
Integrante: Guilherme
DADO que sou um aluno
QUANDO acesso a página principal da plataforma
ENTÃO devo visualizar a lista de cursos disponíveis
<br>
Integrante: Gustavo
DADO que eu sou um aluno autenticado no sistema
E que não existem cursos publicados na plataforma
QUANDO eu acesso a página de "Cursos"
ENTÃO eu devo ver uma mensagem informativa como "Nenhum curso disponível no momento. Volte em breve!"
<br>
Integrante: Giovana
DADO que estou visualizando a lista de cursos disponíveis
E vejo um curso chamado "Gamificação Aplicada à Educação"
QUANDO eu clicar neste curso
ENTÃO devo ser redirecionado para a página de detalhes do curso "Gamificação Aplicada à Educação"
E nesta página devo visualizar um botão de "Assinar Curso"
<br>

Integrante: João
DADO que estou na página de "Cursos"
E que existem cursos das categorias "Tecnologia", "Finanças" e "Marketing" publicados
QUANDO eu selecionar a categoria "Tecnologia"
ENTÃO devo visualizar a lista de cursos da categoria "Tecnologia"
E não devo visualizar nenhum curso das categorias "Finanças" e "Marketing"
<br>
Integrante: Armando
DADO que o aluno está autenticado na plataforma
E existem cursos disponíveis com informações completas (nome, descrição, carga horária, preço e instrutor) 
QUANDO o aluno acessa a página de cursos disponíveis
E seleciona um curso específico
ENTÃO ele deve ser redirecionado para a página de detalhes do curso
E deve visualizar todas as informações do curso
E ter a opção de assinar o curso
