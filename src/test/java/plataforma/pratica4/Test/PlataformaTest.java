package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.PlataformaCursos;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import java.util.List;


public class PlataformaTest {

    // BDD Gustavo
    @Test
    public void deveExibirMensagemQuandoNaoExistemCursosDisponiveis() {
        
        
        Aluno alunoAutenticado = new Aluno("João da Silva");
        
        
        PlataformaCursos plataforma = new PlataformaCursos();
        
        
        String mensagemRetornada = plataforma.listarCursosPara(alunoAutenticado);
        
       
        String mensagemEsperada = "Nenhum curso disponível no momento. Volte em breve!";
        assertEquals(mensagemEsperada, mensagemRetornada);
    }

    // BDD Giovana
    @Test
    public void deveRedirecionarParaPaginaDeDetalhesAoClicarNoCurso() {

        
        PlataformaCursos plataforma = new PlataformaCursos();
        Curso cursoDesejado = new Curso("Gamificação Aplicada à Educação", Categoria.TECNOLOGIA);
        Curso outroCurso = new Curso("Java Básico", Categoria.TECNOLOGIA);
        
        plataforma.adicionarCurso(cursoDesejado);
        plataforma.adicionarCurso(outroCurso);
        
        Aluno aluno = new Aluno("Carlos"); 
        
        Curso paginaDestino = plataforma.selecionarCurso(aluno, cursoDesejado);

       
        assertNotNull(paginaDestino, "A página de destino não deveria ser nula.");
        
        
        assertEquals("Gamificação Aplicada à Educação", paginaDestino.getCurso().getNome());

        
        assertTrue(paginaDestino.possuiBotaoDeAssinatura(), "A página de detalhes deveria exibir o botão 'Assinar Curso'.");
    }

    // BDD Guilherme
    @Test
    public void deveListarCursosQuandoExitemCursosDisponiveis() {
        
    	Aluno alunoAutenticado = new Aluno("João da Silva");

        
    	PlataformaCursos plataforma = new PlataformaCursos();

    	
    	Curso cursoJavaBasico = new Curso("Curso de Java basico", Categoria.TECNOLOGIA);
    	Curso cursoSpringBoot = new Curso("Curso de Spring Boot", Categoria.TECNOLOGIA);
        
        plataforma.adicionarCurso(cursoJavaBasico);
        plataforma.adicionarCurso(cursoSpringBoot);
        
        String mensagemRetornada = plataforma.listarCursosPara(alunoAutenticado);

        
        String mensagemEsperada = "Cursos disponiveis: Curso de Java basico, Curso de Spring Boot";
        assertEquals(mensagemEsperada, mensagemRetornada);
    }

    // BDD João
    @Test
    public void deveExibirApenasCursosDaCategoriaSelecionada() {

        // DADO que estou na página de "Cursos"
        PlataformaCursos paginaDeCursos = new PlataformaCursos();

        // E que existem cursos das categorias "Tecnologia", "Finanças" e "Marketing" publicados
        Curso cursoJava = new Curso("Java para Web", Categoria.TECNOLOGIA);
        Curso cursoPython = new Curso("Python para Data Science", Categoria.TECNOLOGIA);
        Curso cursoInvestimentos = new Curso("Investimentos na Bolsa", Categoria.FINANCAS);
        Curso cursoMarketingDigital = new Curso("Marketing Digital Essencial", Categoria.MARKETING);

        paginaDeCursos.adicionarCurso(cursoJava);
        paginaDeCursos.adicionarCurso(cursoPython);
        paginaDeCursos.adicionarCurso(cursoInvestimentos);
        paginaDeCursos.adicionarCurso(cursoMarketingDigital);

        // QUANDO eu selecionar a categoria "Tecnologia"
        List<Curso> cursosFiltrados = paginaDeCursos.filtraPorCategoria(Categoria.TECNOLOGIA);

        // ENTÃO devo visualizar a lista de cursos da categoria "Tecnologia"
        assertEquals(2, cursosFiltrados.size());
        assertTrue(cursosFiltrados.stream().anyMatch(c -> c.getNome().equals("Java para Web")));
        assertTrue(cursosFiltrados.stream().anyMatch(c -> c.getNome().equals("Python para Data Science")));

        // E não devo visualizar nenhum curso das categorias "Finanças" e "Marketing"
        assertTrue(cursosFiltrados.stream().noneMatch(c -> c.getCategoria().equals(Categoria.FINANCAS)));
        assertTrue(cursosFiltrados.stream().noneMatch(c -> c.getCategoria().equals(Categoria.MARKETING)));
    }

    // BDD Armando
    @Test
    public void deveExibirDetalhesCompletosDoCursoAoSelecionar() {

        // DADO que o aluno está autenticado na plataforma
        Aluno aluno = new Aluno("Armando");
        PlataformaCursos plataforma = new PlataformaCursos();

        // E existem cursos disponíveis com informações completas
        Curso cursoDetalhado = new Curso(
                "Gamificação Aplicada",
                Categoria.TECNOLOGIA,
                "Aprenda a aplicar conceitos de jogos para engajar usuários.",
                40,
                299.90,
                "Prof. Joana Mota"
        );
        plataforma.adicionarCurso(cursoDetalhado);

        // QUANDO o aluno acessa a página de cursos e seleciona um curso específico
        Curso paginaDeDetalhes = plataforma.selecionarCurso(aluno, cursoDetalhado);

        // ENTÃO ele deve ser redirecionado para a página de detalhes do curso
        assertNotNull(paginaDeDetalhes, "A página de detalhes não pode ser nula após a seleção.");
        assertEquals("Gamificação Aplicada", paginaDeDetalhes.getNome());

        // E deve visualizar todas as informações do curso
        assertEquals("Aprenda a aplicar conceitos de jogos para engajar usuários.", paginaDeDetalhes.getDescricao());
        assertEquals(40, paginaDeDetalhes.getCargaHoraria());
        assertEquals(299.90, paginaDeDetalhes.getPreco());
        assertEquals("Prof. Joana Mota", paginaDeDetalhes.getInstrutor());

        // E ter a opção de assinar o curso
        assertTrue(paginaDeDetalhes.possuiBotaoDeAssinatura(), "A página de detalhes deve exibir a opção de assinar o curso.");
    }

    @Test
    public void deveExibirOProgressoDoAlunoEmUmCursoInscrito() {

        // DADO que sou um aluno autenticado no sistema
        Aluno aluno = new Aluno("João da Silva");
        Curso curso = new Curso("Gamificação Aplicada à Educação", Categoria.TECNOLOGIA);

        // E estou inscrito no curso "Gamificação Aplicada à Educação"
        Inscricao inscricao = new Inscricao(curso);
        inscricao.setProgresso(45.0); // Simula que o aluno já progrediu 45%
        aluno.inscrever(inscricao);

        // QUANDO eu clico no curso "Gamificação Aplicada à Educação"
        String nome_aluno = aluno.getNome();
        double progressoObtido = aluno.verProgresso(curso);

        // ENTÃO eu visualizo o meu progresso nesse curso
        assertEquals(45.0, progressoObtido, 0.00001);
    }
    
}

