package plataforma.pratica4.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CursoTest {

    @Test
    public void deveCriarCursoCorretamente() {
        // Teste Unitário puro: sem Spring, sem Banco, sem Mocks complexos.
        // Testa apenas a integridade do objeto e construtores.
        
        Curso curso = new Curso("Docker", Categoria.TECNOLOGIA, "Descricao", 10, 100.0, "Instrutor");
        
        // Valida se os dados foram atribuídos corretamente (Lombok @Data e Construtor)
        //
        assertEquals("Docker", curso.getNome());
        assertEquals(Categoria.TECNOLOGIA, curso.getCategoria());
        assertEquals(100.0, curso.getPreco());
        
        // O ID deve ser nulo antes da persistência
        assertNull(curso.getId());
    }
}