package plataforma.pratica4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.repository.AlunoRepository;
import plataforma.pratica4.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;
    private Curso curso;
    private final Long ID_ALUNO = 1L;
    private final Long ID_CURSO = 10L;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Teste");
        curso = new Curso("Docker", Categoria.TECNOLOGIA);
        aluno.setId(ID_ALUNO); 
        curso.setId(ID_CURSO); 
    }
    
    // --- Testes de Matrícula (Cobrindo todas as ramificações do IF/ELSE) ---
    @Test
    public void deveMatricularAlunoComSucesso() {
        // Cenário: Mocks retornam valores presentes (Caminho TRUE)
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(ID_CURSO)).thenReturn(Optional.of(curso));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno); 
        
        Aluno alunoAtualizado = alunoService.matricularAluno(ID_ALUNO, ID_CURSO);

        assertNotNull(alunoAtualizado);
        assertEquals(1, alunoAtualizado.getInscricoes().size());
    }

    @Test
    public void deveLancarExcecaoQuandoAlunoNaoExiste() {
        // Cenário: Aluno não encontrado (Caminho FALSE, 1ª parte do &&)
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.empty());
        when(cursoRepository.findById(ID_CURSO)).thenReturn(Optional.of(curso)); // Deve ser mockado para simular o cenário
        
        assertThrows(RuntimeException.class, () -> {
            alunoService.matricularAluno(ID_ALUNO, ID_CURSO);
        }, "Deveria lançar exceção quando o aluno está ausente.");
    }
    
    @Test
    public void deveLancarExcecaoQuandoCursoNaoExiste() {
        // Cenário: Aluno encontrado, Curso NÃO encontrado (Caminho FALSE, 2ª parte do &&)
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(ID_CURSO)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            alunoService.matricularAluno(ID_ALUNO, ID_CURSO);
        }, "Deveria lançar exceção quando o curso está ausente.");
    }

    // --- Cobertura dos Métodos Básicos (FindAll, Save, FindById) ---
    @Test
    public void deveListarTodosOsAlunos() {
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno, new Aluno("Joana")));
        
        List<Aluno> lista = alunoService.listarTodos();
        
        assertEquals(2, lista.size());
        verify(alunoRepository, times(1)).findAll();
    }
    
    @Test
    public void deveCriarNovoAluno() {
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        
        Aluno novo = alunoService.criarAluno(aluno);
        
        assertNotNull(novo);
        assertEquals("Teste", novo.getNome());
        verify(alunoRepository, times(1)).save(aluno);
    }
    
    @Test
    public void deveBuscarAlunoPorIdComSucesso() {
        when(alunoRepository.findById(ID_ALUNO)).thenReturn(Optional.of(aluno));
        
        Optional<Aluno> resultado = alunoService.buscarPorId(ID_ALUNO);
        
        assertTrue(resultado.isPresent());
        assertEquals("Teste", resultado.get().getNome());
    }
    
    @Test
    public void deveRetornarOptionalVazioAoBuscarAlunoInexistente() {
        when(alunoRepository.findById(2L)).thenReturn(Optional.empty());
        
        Optional<Aluno> resultado = alunoService.buscarPorId(2L);
        
        assertFalse(resultado.isPresent());
    }
}