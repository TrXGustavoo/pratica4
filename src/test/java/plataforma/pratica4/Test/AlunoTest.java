package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.dominio.Progresso;

public class AlunoTest {

    private Aluno aluno;
    private Curso cursoJava;
    private Curso cursoPython;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno Teste");
        cursoJava = new Curso("Curso de Java", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        cursoPython = new Curso("Curso de Python", Categoria.TECNOLOGIA, null, 0, 0.0, null);
    }

    @Test
    public void deveRetornarProgressoZeroSeNaoEstiverInscrito() {
        assertEquals(0.0, aluno.verProgresso(cursoJava));
    }

    @Test
    public void deveRetornarProgressoCorretoDoCurso() {
        Inscricao inscricaoJava = new Inscricao(cursoJava);
        inscricaoJava.atualizarProgresso(new Progresso(50.0));
        aluno.inscrever(inscricaoJava);

        Inscricao inscricaoPython = new Inscricao(cursoPython);
        inscricaoPython.atualizarProgresso(new Progresso(25.0));
        aluno.inscrever(inscricaoPython);

        // Valida que o progresso de cada curso é retornado corretamente
        assertEquals(50.0, aluno.verProgresso(cursoJava));
        assertEquals(25.0, aluno.verProgresso(cursoPython));
    }

    @Test
    public void deveRetornarProgressoZeroParaCursoNaoInscritoMesmoTendoOutrasInscricoes() {
        Inscricao inscricaoJava = new Inscricao(cursoJava);
        inscricaoJava.atualizarProgresso(new Progresso(50.0));
        aluno.inscrever(inscricaoJava);

        // Aluno está inscrito em Java, mas perguntamos sobre Python
        assertEquals(0.0, aluno.verProgresso(cursoPython));
    }
}