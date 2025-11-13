package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import plataforma.pratica4.dominio.Progresso;

public class ProgressoTest {

    @Test
    public void deveCriarProgressoComValoresValidos() {
        // Testa os limites
        Progresso pMin = new Progresso(0.0);
        Progresso pMax = new Progresso(100.0);
        Progresso pMeio = new Progresso(50.0);

        assertEquals(0.0, pMin.getValor());
        assertEquals(100.0, pMax.getValor());
        assertEquals(50.0, pMeio.getValor());
    }

    @Test
    public void deveLancarExcecaoParaProgressoAbaixoDeZero() {
        // Testa o limite inferior inválido
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(-0.1)
        );

        assertEquals("O valor do progresso deve estar entre 0 e 100.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoParaProgressoAcimaDeCem() {
        // Testa o limite superior inválido
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Progresso(100.1)
        );

        assertEquals("O valor do progresso deve estar entre 0 e 100.", exception.getMessage());
    }

    @Test
    public void deveSerConsideradoIgualParaOMesmoValor() {
        // Valida o comportamento de Objeto de Valor (igualdade)
        Progresso p1 = new Progresso(75.0);
        Progresso p2 = new Progresso(75.0);

        assertEquals(p1, p2);
        assertTrue(p1.hashCode() == p2.hashCode());
    }

    @Test
    public void deveSerConsideradoDiferenteParaValoresDiferentes() {
        Progresso p1 = new Progresso(75.0);
        Progresso p2 = new Progresso(75.1);

        assertNotEquals(p1, p2);
    }
}