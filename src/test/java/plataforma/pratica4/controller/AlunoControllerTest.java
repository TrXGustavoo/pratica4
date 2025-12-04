package plataforma.pratica4.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import plataforma.pratica4.domain.model.Aluno;
import plataforma.pratica4.domain.service.AlunoService;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    private Aluno aluno;
    private final Long ID_ALUNO = 1L;
    private final Long ID_CURSO = 10L;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Teste");
        aluno.setId(ID_ALUNO);
    }
    
    // --- BDD 1: Cobertura de sucesso (HTTP 200) ---
    @Test
    public void deveRetornarStatus200eListaDeAlunos() throws Exception {
        when(alunoService.listarTodos()).thenReturn(Arrays.asList(aluno));

        mockMvc.perform(get("/alunos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste"));
    }
    
    // --- BDD 2: Cobertura de criação (HTTP 201 Created) ---
    @Test
    public void deveRetornarStatus201AoCriarNovoAluno() throws Exception {
        when(alunoService.criarAluno(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Novo Aluno\"}"))
                .andExpect(status().isCreated()) // Cobre o ResponseEntity.created()
                .andExpect(header().string("Location", "/alunos/" + ID_ALUNO)); // Verifica o header
    }

    // --- BDD 3: Cobertura do Caminho TRY (Matrícula com Sucesso) ---
    @Test
    public void deveRetornarStatus200AoMatricularComSucesso() throws Exception {
        // Simula o service retornando sucesso (caminho 'try')
        when(alunoService.matricularAluno(ID_ALUNO, ID_CURSO)).thenReturn(aluno);

        mockMvc.perform(post("/alunos/{idAluno}/matricular/{idCurso}", ID_ALUNO, ID_CURSO)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Cobre o 'return ResponseEntity.ok(alunoAtualizado);'
                .andExpect(jsonPath("$.nome").value("Teste"));
    }

    // --- BDD 4: Cobertura do Caminho CATCH (Matrícula com Falha) ---
    @Test
    public void deveRetornarStatus400QuandoMatriculaFalha() throws Exception {
        // Simula o service lançando exceção (caminho 'catch')
        when(alunoService.matricularAluno(ID_ALUNO, ID_CURSO)).thenThrow(new RuntimeException("Não encontrado."));

        mockMvc.perform(post("/alunos/{idAluno}/matricular/{idCurso}", ID_ALUNO, ID_CURSO)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Cobre o 'return ResponseEntity.badRequest().build();'
    }
}