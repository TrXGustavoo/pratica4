package plataforma.pratica4.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CursoTest {

    @Test
    public void deveCriarCursoComConstrutorCompleto() {
        // Testa o construtor completo
        Curso curso = new Curso("Gamificação", Categoria.TECNOLOGIA, "Descrição", 40, 299.90, "Prof. X");
        
        assertEquals("Gamificação", curso.getNome());
        assertEquals(Categoria.TECNOLOGIA, curso.getCategoria());
        assertEquals("Descrição", curso.getDescricao());
        assertEquals(40, curso.getCargaHoraria());
        assertEquals(299.90, curso.getPreco());
        assertEquals("Prof. X", curso.getInstrutor());
        assertNull(curso.getId()); // O ID deve ser nulo antes da persistência
    }

    @Test
    public void deveCriarCursoComConstrutorSimples() {
        // Testa o construtor parcial, garantindo que os campos não definidos sejam valores padrão
        Curso curso = new Curso("Java Básico", Categoria.TECNOLOGIA);
        
        assertEquals("Java Básico", curso.getNome());
        assertEquals(Categoria.TECNOLOGIA, curso.getCategoria());
        
        // Campos que não foram inicializados nos construtores devem ser valores padrão (null ou 0)
        assertNull(curso.getDescricao());
        assertNull(curso.getInstrutor());
        assertEquals(0, curso.getCargaHoraria());
        assertEquals(0.0, curso.getPreco());
    }
}