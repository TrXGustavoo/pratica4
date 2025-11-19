package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.dominio.Progresso;
import plataforma.pratica4.dominio.Aluno; 

public class InscricaoTest {

    // Helper para criar um Aluno simples
    private Aluno criarAluno() {
        Aluno aluno = new Aluno("Aluno Teste");
        aluno.setId(1L);
        return aluno;
    }

    // Helper para criar um Curso simples
    private Curso criarCurso() {
        Curso curso = new Curso("Curso de Java", Categoria.TECNOLOGIA, "Descricao", 40, 0.0, "Prof");
        curso.setId(10L); 
        return curso;
    }

    /**
     * Testa o construtor da Entidade, verificando relacionamentos e estado inicial.
     * Cobre o construtor explícito e todos os getters gerados pelo Lombok (@Data).
     */
    @Test
    public void deveCriarInscricaoComProgressoZeroECobrirGetters() {
        // Cenário
        Aluno aluno = criarAluno();
        Curso curso = criarCurso();
        
        // Ação
        Inscricao inscricao = new Inscricao(aluno, curso);
        inscricao.setId(100L); // Cobre o setter/getter do ID

        // Verificação: Cobre todos os getters do ID, Aluno, Curso e Progresso
        assertNotNull(inscricao.getCurso(), "O curso não deve ser nulo.");
        assertEquals(aluno.getId(), inscricao.getAluno().getId());
        assertEquals(curso.getNome(), inscricao.getCurso().getNome());
        assertEquals(100L, inscricao.getId()); 
        
        // Verifica o estado inicial do Progresso
        assertNotNull(inscricao.getProgresso());
        assertEquals(0.0, inscricao.getProgresso().getValor(), "O progresso inicial deve ser 0.0");
    }

    /**
     * Testa o método atualizarProgresso.
     * Cobre o caminho da única ramificação de código restante.
     */
    @Test
    public void deveAtualizarProgressoCorretamente() {
        // Cenário
        Aluno aluno = criarAluno();
        Curso curso = criarCurso();
        Inscricao inscricao = new Inscricao(aluno, curso);

        // Estado inicial
        assertEquals(0.0, inscricao.getProgresso().getValor());

        // Ação: Atualiza o progresso
        Progresso novoProgresso = new Progresso(45.5);
        inscricao.atualizarProgresso(novoProgresso);

        // Verificação
        assertEquals(45.5, inscricao.getProgresso().getValor());
        assertEquals(novoProgresso, inscricao.getProgresso(), "A referência do Progresso deve ter sido atualizada.");
    }
}