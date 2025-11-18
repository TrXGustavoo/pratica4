package plataforma.pratica4.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

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

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Teste");
        curso = new Curso("Docker", Categoria.TECNOLOGIA);
        
        // Simular que o Aluno e Curso têm IDs (necessário para JPA)
        aluno.setId(1L); 
        curso.setId(10L); 
    }
    
    // --- Caminho de Sucesso (Cobre o 'if' como true) ---
    @Test
    public void deveMatricularAlunoComSucesso() {
        // Cenário: Mocks retornam valores presentes
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno); // Simula o salvamento
        
        // Ação
        Aluno alunoAtualizado = alunoService.matricularAluno(1L, 10L);

        // Verificação
        assertNotNull(alunoAtualizado);
        assertEquals(1, alunoAtualizado.getInscricoes().size());
    }

    // --- Caminho de Falha (Cobre o 'else' e a exceção) ---
    @Test
    public void deveLancarExcecaoQuandoAlunoNaoExiste() {
        // Cenário: Aluno não encontrado
        when(alunoRepository.findById(1L)).thenReturn(Optional.empty());
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));
        
        // Verificação
        assertThrows(RuntimeException.class, () -> {
            alunoService.matricularAluno(1L, 10L);
        }, "Deveria lançar exceção quando o aluno está ausente.");
    }
    
    // Teste Bônus: Criação e Listagem (para cobertura)
    @Test
    public void deveCriarNovoAluno() {
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        
        Aluno novo = alunoService.criarAluno(aluno);
        
        assertNotNull(novo);
        assertEquals("Teste", novo.getNome());
    }
}