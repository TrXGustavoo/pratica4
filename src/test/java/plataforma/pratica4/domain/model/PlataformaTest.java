package plataforma.pratica4.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
        assertEquals("Gamificação Aplicada à Educação", paginaDestino.getNome());
        // A asserção sobre o botão foi removida (não é responsabilidade do domínio)
    }

    // BDD Guilherme
    @Test
    public void deveListarCursosQuandoExitemCursosDisponiveis() {
    	Aluno alunoAutenticado = new Aluno("João da Silva");
    	PlataformaCursos plataforma = new PlataformaCursos();

        // Usar o construtor que temos
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
        PlataformaCursos paginaDeCursos = new PlataformaCursos();

        Curso cursoJava = new Curso("Java para Web", Categoria.TECNOLOGIA);
        Curso cursoPython = new Curso("Python para Data Science", Categoria.TECNOLOGIA);
        Curso cursoInvestimentos = new Curso("Investimentos na Bolsa", Categoria.FINANCAS);
        Curso cursoMarketingDigital = new Curso("Marketing Digital Essencial", Categoria.MARKETING);

        paginaDeCursos.adicionarCurso(cursoJava);
        paginaDeCursos.adicionarCurso(cursoPython);
        paginaDeCursos.adicionarCurso(cursoInvestimentos);
        paginaDeCursos.adicionarCurso(cursoMarketingDigital);

        List<Curso> cursosFiltrados = paginaDeCursos.filtraPorCategoria(Categoria.TECNOLOGIA);

        assertEquals(2, cursosFiltrados.size());
        assertTrue(cursosFiltrados.stream().anyMatch(c -> c.getNome().equals("Java para Web")));
        assertTrue(cursosFiltrados.stream().anyMatch(c -> c.getNome().equals("Python para Data Science")));
        assertTrue(cursosFiltrados.stream().noneMatch(c -> c.getCategoria().equals(Categoria.FINANCAS)));
        assertTrue(cursosFiltrados.stream().noneMatch(c -> c.getCategoria().equals(Categoria.MARKETING)));
    }

    // BDD Armando 
    @Test
    public void deveExibirDetalhesCompletosDoCursoAoSelecionar() {
        Aluno aluno = new Aluno("Armando");
        PlataformaCursos plataforma = new PlataformaCursos();

        Curso cursoDetalhado = new Curso(
                "Gamificação Aplicada",
                Categoria.TECNOLOGIA,
                "Aprenda a aplicar conceitos de jogos para engajar usuários.",
                40,
                299.90,
                "Prof. Joana Mota"
        );
        plataforma.adicionarCurso(cursoDetalhado);

        // A variável correta é 'paginaDestino'
        Curso paginaDestino = plataforma.selecionarCurso(aluno, cursoDetalhado);

        assertNotNull(paginaDestino, "A página de destino não pode ser nula após a seleção.");
        
        // CORREÇÃO: Alterado de 'paginaDeDetalhes' para 'paginaDestino'
        assertEquals("Gamificação Aplicada", paginaDestino.getNome());
        assertEquals("Aprenda a aplicar conceitos de jogos para engajar usuários.", paginaDestino.getDescricao());
        assertEquals(40, paginaDestino.getCargaHoraria());
        assertEquals(299.90, paginaDestino.getPreco(), 0.001);
        assertEquals("Prof. Joana Mota", paginaDestino.getInstrutor());
    }

    // Teste de Progresso (Corrigido)
    @Test
    public void deveExibirOProgressoDoAlunoEmUmCursoInscrito() {
        Aluno aluno = new Aluno("João da Silva");
        Curso curso = new Curso("Gamificação Aplicada à Educação", Categoria.TECNOLOGIA);
        
        // CORREÇÃO: Usar o construtor de Inscrição correto
        Inscricao inscricao = new Inscricao(aluno, curso); 

        inscricao.atualizarProgresso(new Progresso(45.0)); 
        aluno.inscrever(inscricao);
        
        double progressoObtido = aluno.verProgresso(curso);

        assertEquals(45.0, progressoObtido, 0.001);
    }
    
    // Teste de Branch (Corrigido)
    @Test
    public void deveRetornarNuloAoSelecionarCursoInexistente() {
        PlataformaCursos plataforma = new PlataformaCursos();
        Curso cursoExistente = new Curso("Java", Categoria.TECNOLOGIA);
        plataforma.adicionarCurso(cursoExistente);
        
        Curso cursoInexistente = new Curso("Python", Categoria.TECNOLOGIA);
        Aluno aluno = new Aluno("Aluno");

        Curso paginaDestino = plataforma.selecionarCurso(aluno, cursoInexistente);

        org.junit.jupiter.api.Assertions.assertNull(paginaDestino, "Deveria retornar nulo ao selecionar um curso que não existe.");
    }
}