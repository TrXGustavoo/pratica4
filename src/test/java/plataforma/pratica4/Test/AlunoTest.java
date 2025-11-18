package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
    private Curso cursoInexistente;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno Teste");
        cursoJava = new Curso("Curso de Java", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        cursoPython = new Curso("Curso de Python", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        cursoInexistente = new Curso("Curso Inexistente", Categoria.TECNOLOGIA, null, 0, 0.0, null);
    }
    
    // ... (Métodos verProgresso devem estar OK) ...

    // --- TESTES PARA O MÉTODO OPTIONAL (Branch Coverage) ---

    @Test
    public void deveRetornarOptionalComProgressoSeInscrito() {
        // Cenário: Aluno está inscrito
        Inscricao inscricaoJava = new Inscricao(aluno, cursoJava);
        inscricaoJava.atualizarProgresso(new Progresso(50.0));
        aluno.inscrever(inscricaoJava);

        // Ação
        Optional<Progresso> progressoOpt = aluno.getProgresso(cursoJava);

        // Verificação: Optional presente
        assertTrue(progressoOpt.isPresent());
        assertEquals(50.0, progressoOpt.get().getValor());
    }

    @Test
    public void deveRetornarOptionalVazioSeNaoEstiverInscrito() {
        // Ação: Busca por curso sem inscrição
        Optional<Progresso> progressoOpt = aluno.getProgresso(cursoInexistente);

        // Verificação: Optional ausente
        assertFalse(progressoOpt.isPresent());
    }
}