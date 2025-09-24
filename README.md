# Prática 4 e 6

Repositório utilizado para as Práticas 4 e 6 da disciplina de DevOps.

## User Story e BDDs
User Story Escolhida: COMO aluno QUERO poder visualizar a lista de cursos disponíveis PARA que eu possa escolher qual curso assinar.
<br>
<br>
Integrante: Guilherme<br>
DADO que sou um aluno<br>
QUANDO acesso a página principal da plataforma<br>
ENTÃO devo visualizar a lista de cursos disponíveis<br>
<br>
Integrante: Gustavo<br>
DADO que eu sou um aluno autenticado no sistema<br>
E que não existem cursos publicados na plataforma<br>
QUANDO eu acesso a página de "Cursos"<br>
ENTÃO eu devo ver uma mensagem informativa como "Nenhum curso disponível no momento. Volte em breve!"
<br><br>
Integrante: Giovana<br>
DADO que estou visualizando a lista de cursos disponíveis<br>
E vejo um curso chamado "Gamificação Aplicada à Educação"<br>
QUANDO eu clicar neste curso<br>
ENTÃO devo ser redirecionado para a página de detalhes do curso "Gamificação Aplicada à Educação"<br>
E nesta página devo visualizar um botão de "Assinar Curso"<br>
<br>

Integrante: João<br>
DADO que estou na página de "Cursos"<br>
E que existem cursos das categorias "Tecnologia", "Finanças" e "Marketing" publicados<br>
QUANDO eu selecionar a categoria "Tecnologia"<br>
ENTÃO devo visualizar a lista de cursos da categoria "Tecnologia"<br>
E não devo visualizar nenhum curso das categorias "Finanças" e "Marketing"<br>
<br>
Integrante: Armando<br>
DADO que o aluno está autenticado na plataforma<br>
E existem cursos disponíveis com informações completas (nome, descrição, carga horária, preço e instrutor)<br> 
QUANDO o aluno acessa a página de cursos disponíveis<br>
E seleciona um curso específico<br>
ENTÃO ele deve ser redirecionado para a página de detalhes do curso<br>
E deve visualizar todas as informações do curso<br>
E ter a opção de assinar o curso<br>
