package plataforma.pratica4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dto.CursoDTO;
import plataforma.pratica4.repository.CursoRepository;

@ExtendWith(MockitoExtension.class) // Habilita o uso de anotações @Mock e @InjectMocks sem Spring
public class CursoServiceTest {

    @Mock // Cria um "dublê" do repositório
    private CursoRepository cursoRepository;

    @InjectMocks // Injeta o mock acima dentro da instância real do Service
    private CursoService cursoService;

    @Test
    public void deveListarTodosOsCursosConvertidosParaDTO() {
        // Cenário
        Curso curso1 = new Curso("Java", Categoria.TECNOLOGIA);
        // Precisamos setar IDs pois o mock não gera IDs automáticos como o banco
        // (Poderíamos usar Reflection ou um setter de teste se necessário, mas aqui o foco é a lista)
        
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso1)); // Define comportamento do Mock

        // Ação
        List<CursoDTO> resultado = cursoService.listarTodos();

        // Verificação
        assertEquals(1, resultado.size());
        assertEquals("Java", resultado.get(0).getNome());
        assertEquals("TECNOLOGIA", resultado.get(0).getCategoria());
        
        // Verifica se o repositório foi chamado exatamente uma vez
        verify(cursoRepository, times(1)).findAll();
    }
}