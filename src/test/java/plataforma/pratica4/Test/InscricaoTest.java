package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.dominio.Progresso;

public class InscricaoTest {

    @Test
    public void deveCriarInscricaoComProgressoZero() {
        // Usa o construtor gerado pelo Lombok
        Curso curso = new Curso("Curso de Java", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        Inscricao inscricao = new Inscricao(curso);

        assertNotNull(inscricao.getCurso());
        assertEquals(curso, inscricao.getCurso());

        // Valida que o progresso é inicializado em 0.0 por padrão
        assertNotNull(inscricao.getProgresso());
        assertEquals(0.0, inscricao.getProgresso().getValor());
    }

    @Test
    public void deveAtualizarProgressoCorretamente() {
        Curso curso = new Curso("Curso de Python", Categoria.TECNOLOGIA, null, 0, 0.0, null);
        Inscricao inscricao = new Inscricao(curso);

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