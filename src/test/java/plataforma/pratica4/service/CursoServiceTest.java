package plataforma.pratica4.service;

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

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dto.CursoDTO;
import plataforma.pratica4.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    // --- Cobertura do Caminho Feliz de ListarTodos (Já existia, mas completo) ---
    @Test
    public void deveListarTodosOsCursosConvertidosParaDTO() {
        Curso curso1 = new Curso("Java", Categoria.TECNOLOGIA);
        curso1.setId(1L); // Adicionar ID para o DTO
        
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso1)); 

        List<CursoDTO> resultado = cursoService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Java", resultado.get(0).getNome());
        verify(cursoRepository, times(1)).findAll();
    }
    
    // --- Cobertura da Ramificação: Lista Vazia em ListarTodos ---
    @Test
    public void deveRetornarListaVaziaQuandoNaoHaCursos() {
        when(cursoRepository.findAll()).thenReturn(Collections.emptyList());

        List<CursoDTO> resultado = cursoService.listarTodos();

        assertEquals(0, resultado.size());
        verify(cursoRepository, times(1)).findAll();
    }

    // --- Cobertura da Ramificação: Filtro de Categoria com Sucesso ---
    @Test
    public void deveFiltrarCursosPorCategoriaComSucesso() {
        Curso cursoTec = new Curso("Tech Course", Categoria.TECNOLOGIA);
        cursoTec.setId(2L);
        
        when(cursoRepository.findByCategoria(Categoria.TECNOLOGIA)).thenReturn(Arrays.asList(cursoTec));
        
        List<CursoDTO> resultado = cursoService.buscarPorCategoria(Categoria.TECNOLOGIA);
        
        assertEquals(1, resultado.size());
        assertEquals("Tech Course", resultado.get(0).getNome());
        verify(cursoRepository, times(1)).findByCategoria(Categoria.TECNOLOGIA);
    }
    
    // --- Cobertura da Ramificação: Filtro de Categoria Vazio ---
    @Test
    public void deveRetornarListaVaziaAoFiltrarSemResultados() {
        when(cursoRepository.findByCategoria(Categoria.MARKETING)).thenReturn(Collections.emptyList());
        
        List<CursoDTO> resultado = cursoService.buscarPorCategoria(Categoria.MARKETING);
        
        assertEquals(0, resultado.size());
        verify(cursoRepository, times(1)).findByCategoria(Categoria.MARKETING);
    }
    
    // --- Cobertura Adicional (Método Salvar) ---
    @Test
    public void deveSalvarUmNovoCurso() {
        Curso novoCurso = new Curso("Novo", Categoria.TECNOLOGIA);
        when(cursoRepository.save(novoCurso)).thenReturn(novoCurso);

        Curso salvo = cursoService.criarCurso(novoCurso);
        
        assertEquals("Novo", salvo.getNome());
        verify(cursoRepository, times(1)).save(novoCurso);
    }
}