package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.dominio.Progresso;
import plataforma.pratica4.dominio.Aluno; // 1. Importar Aluno

public class InscricaoTest {

    @Test
    public void deveCriarInscricaoComProgressoZero() {
        // 2. Criar Aluno e Curso
        Aluno aluno = new Aluno("Aluno Teste");
        Curso curso = new Curso("Curso de Java", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        
        // 3. Usar o construtor correto
        Inscricao inscricao = new Inscricao(aluno, curso);

        assertNotNull(inscricao.getCurso());
        assertEquals(curso, inscricao.getCurso());
        assertNotNull(inscricao.getAluno());
        assertEquals(aluno, inscricao.getAluno());

        assertNotNull(inscricao.getProgresso());
        assertEquals(0.0, inscricao.getProgresso().getValor());
    }

    @Test
    public void deveAtualizarProgressoCorretamente() {
        // 4. Criar Aluno e Curso
        Aluno aluno = new Aluno("Aluno Teste");
        Curso curso = new Curso("Curso de Python", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        
        // 5. Usar o construtor correto
        Inscricao inscricao = new Inscricao(aluno, curso);

        // Estado inicial
        assertEquals(0.0, inscricao.getProgresso().getValor());

        // Atualiza o progresso
        Progresso novoProgresso = new Progresso(45.5);
        inscricao.atualizarProgresso(novoProgresso);

        // Valida o novo estado
        assertEquals(45.5, inscricao.getProgresso().getValor());
        assertEquals(novoProgresso, inscricao.getProgresso());
    }
}