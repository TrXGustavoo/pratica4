package plataforma.pratica4.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import plataforma.pratica4.domain.model.Categoria;
import plataforma.pratica4.domain.model.Curso;
import plataforma.pratica4.application.dto.CursoDTO;
import plataforma.pratica4.domain.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    public void deveListarTodosOsCursosConvertidosParaDTO() {
        Curso curso1 = new Curso("Java", Categoria.TECNOLOGIA);
        curso1.setId(1L); 
        
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso1)); 

        List<CursoDTO> resultado = cursoService.listarTodos();

        assertEquals(1, resultado.size());
        verify(cursoRepository, times(1)).findAll();
    }
    
    @Test
    public void deveRetornarListaVaziaQuandoNaoHaCursosEmListarTodos() {
        when(cursoRepository.findAll()).thenReturn(Collections.emptyList());

        List<CursoDTO> resultado = cursoService.listarTodos();

        assertEquals(0, resultado.size());
        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    public void deveFiltrarCursosPorCategoriaComSucesso() {
        Curso cursoTec = new Curso("Tech Course", Categoria.TECNOLOGIA);
        cursoTec.setId(2L);
        
        when(cursoRepository.findByCategoria(Categoria.TECNOLOGIA)).thenReturn(Arrays.asList(cursoTec));
        
        List<CursoDTO> resultado = cursoService.buscarPorCategoria(Categoria.TECNOLOGIA);
        
        assertEquals(1, resultado.size());
        verify(cursoRepository, times(1)).findByCategoria(Categoria.TECNOLOGIA);
    }
    
    @Test
    public void deveRetornarListaVaziaAoFiltrarSemResultados() {
        when(cursoRepository.findByCategoria(Categoria.MARKETING)).thenReturn(Collections.emptyList());
        
        List<CursoDTO> resultado = cursoService.buscarPorCategoria(Categoria.MARKETING);
        
        assertEquals(0, resultado.size());
        verify(cursoRepository, times(1)).findByCategoria(Categoria.MARKETING);
    }
    
    @Test
    public void deveSalvarUmNovoCurso() {
        Curso novoCurso = new Curso("Novo", Categoria.TECNOLOGIA);
        when(cursoRepository.save(novoCurso)).thenReturn(novoCurso);

        Curso salvo = cursoService.criarCurso(novoCurso);
        
        assertEquals("Novo", salvo.getNome());
        verify(cursoRepository, times(1)).save(novoCurso);
    }
}