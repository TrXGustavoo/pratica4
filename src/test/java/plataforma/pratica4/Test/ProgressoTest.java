package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import plataforma.pratica4.dominio.Progresso;

public class ProgressoTest {
    
    @Test
    public void deveCriarProgressoComValoresValidos() {
        Progresso pMin = new Progresso(0.0);
        Progresso pMax = new Progresso(100.0);
        Progresso pMeio = new Progresso(50.0);

        assertEquals(0.0, pMin.getValor());
        assertEquals(100.0, pMax.getValor());
        assertEquals(50.0, pMeio.getValor());
    }

    @Test
    public void deveLancarExcecaoParaProgressoAbaixoDeZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(-0.1)
        );
    }

    @Test
    public void deveLancarExcecaoParaProgressoAcimaDeCem() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(100.1)
        );
    }


    @Test
    public void deveSerConsideradoIgualParaOMesmoValor() {
        Progresso p1 = new Progresso(75.0);
        Progresso p2 = new Progresso(75.0);
        Progresso p3 = p1;

        assertTrue(p1.equals(p3));
        
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());
    }
    
    @Test
    public void deveRetornarFalsoParaObjetoNuloOuClasseDiferente() {
        Progresso p1 = new Progresso(10.0);
        String outraClasse = "Teste";
        
        assertFalse(p1.equals(null));
        
        assertFalse(p1.equals(outraClasse));
    }

    @Test
    public void deveFormatarCorretamenteOToString() {
        Progresso p1 = new Progresso(42.5);
        
        assertEquals("42.5%", p1.toString());
    }
}