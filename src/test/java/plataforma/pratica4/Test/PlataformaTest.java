package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.PlataformaCursos;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.dominio.Progresso;
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

        Curso paginaDeDetalhes = plataforma.selecionarCurso(aluno, cursoDetalhado);

        assertNotNull(paginaDeDetalhes, "A página de detalhes não pode ser nula após a seleção.");
        assertEquals("Gamificação Aplicada", paginaDeDetalhes.getNome());
        assertEquals("Aprenda a aplicar conceitos de jogos para engajar usuários.", paginaDeDetalhes.getDescricao());
        assertEquals(40, paginaDeDetalhes.getCargaHoraria());

        assertEquals(299.90, paginaDeDetalhes.getPreco(), 0.001);
        assertEquals("Prof. Joana Mota", paginaDeDetalhes.getInstrutor());
        assertTrue(paginaDeDetalhes.possuiBotaoDeAssinatura(), "A página de detalhes deve exibir a opção de assinar o curso.");
    }

    // Teste de Progresso
    @Test
    public void deveExibirOProgressoDoAlunoEmUmCursoInscrito() {
        Aluno aluno = new Aluno("João da Silva");
        Curso curso = new Curso("Gamificação Aplicada à Educação", Categoria.TECNOLOGIA);
        Inscricao inscricao = new Inscricao(curso);

        // AJUSTE 3: Usamos o método 'atualizarProgresso' com uma nova instância de 'Progresso'.
        // O método setProgresso(double) não existe mais.
        inscricao.atualizarProgresso(new Progresso(45.0)); 
        aluno.inscrever(inscricao);
        
        double progressoObtido = aluno.verProgresso(curso);

        assertEquals(45.0, progressoObtido, 0.001);
    }
}