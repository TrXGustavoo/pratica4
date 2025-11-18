package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import plataforma.pratica4.dominio.Progresso;

public class ProgressoTest {

    // --- Testes de Construtor e Limites (Branch Coverage do 'if' principal) ---
    
    @Test
    public void deveCriarProgressoComValoresValidos() {
        // Testa os limites (caminho feliz do construtor)
        Progresso pMin = new Progresso(0.0);
        Progresso pMax = new Progresso(100.0);
        Progresso pMeio = new Progresso(50.0);

        assertEquals(0.0, pMin.getValor());
        assertEquals(100.0, pMax.getValor());
        assertEquals(50.0, pMeio.getValor());
    }

    @Test
    public void deveLancarExcecaoParaProgressoAbaixoDeZero() {
        // Testa a ramificação valor < 0.0
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(-0.1)
        );

        assertEquals("O valor do progresso deve estar entre 0 e 100.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoParaProgressoAcimaDeCem() {
        // Testa a ramificação valor > 100.0
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(100.1)
        );

        assertEquals("O valor do progresso deve estar entre 0 e 100.", exception.getMessage());
    }

    // --- Testes de Value Object (Equals, HashCode e toString) ---

    @Test
    public void deveSerConsideradoIgualParaOMesmoValor() {
        Progresso p1 = new Progresso(75.0);
        Progresso p2 = new Progresso(75.0);
        Progresso p3 = p1;

        // 1. Igualdade de referência (cobre if (this == o))
        assertTrue(p1.equals(p3));
        
        // 2. Igualdade de valor (cobre a comparação final de atributos)
        assertTrue(p1.equals(p2));
        
        // 3. Verifica HashCode (necessário para coleções)
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void deveSerConsideradoDiferenteParaValoresDiferentes() {
        Progresso p1 = new Progresso(75.0);
        Progresso p2 = new Progresso(75.1);

        assertNotEquals(p1, p2);
    }
    
    @Test
    public void deveRetornarFalsoParaObjetoNulo() {
        Progresso p1 = new Progresso(10.0);
        
        // Cobre a ramificação if (o == null) do equals
        assertFalse(p1.equals(null));
    }

    @Test
    public void deveRetornarFalsoParaClasseDiferente() {
        Progresso p1 = new Progresso(10.0);
        String outraClasse = "Teste";
        
        // Cobre a ramificação if (getClass() != o.getClass())
        assertFalse(p1.equals(outraClasse));
    }

    @Test
    public void deveFormatarCorretamenteOToString() {
        Progresso p1 = new Progresso(42.5);
        
        // Cobre o método toString
        assertEquals("42.5%", p1.toString());
    }
}