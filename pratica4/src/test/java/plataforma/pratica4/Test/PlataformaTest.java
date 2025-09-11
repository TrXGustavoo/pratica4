package plataforma.pratica4.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.PlataformaCursos;

public class CursosTest {

    @Test
    public void deveExibirMensagemQuandoNaoExistemCursosDisponiveis() {
        
        
        Aluno alunoAutenticado = new Aluno("João da Silva");
        
        
        PlataformaCursos plataforma = new PlataformaCursos();
        
        
        String mensagemRetornada = plataforma.listarCursosPara(alunoAutenticado);
        
       
        String mensagemEsperada = "Nenhum curso disponível no momento. Volte em breve!";
        assertEquals(mensagemEsperada, mensagemRetornada);
    }
    
   
}
