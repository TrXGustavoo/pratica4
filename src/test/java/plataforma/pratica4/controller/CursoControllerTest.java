package plataforma.pratica4.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.application.dto.CursoDTO;
import plataforma.pratica4.application.service.CursoService;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;
    
    private Curso curso;
    private CursoDTO cursoDTO;
    private final Long ID_CURSO = 5L;

    @BeforeEach
    void setUp() {
        curso = new Curso("Python", Categoria.TECNOLOGIA);
        curso.setId(ID_CURSO);
        cursoDTO = CursoDTO.fromEntity(curso);
    }

    // --- BDD 1: Listar Todos (Sucesso) ---
    @Test
    public void deveRetornarStatus200eListaDeCursos() throws Exception {
        when(cursoService.listarTodos()).thenReturn(Arrays.asList(cursoDTO));

        mockMvc.perform(get("/cursos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$[0].nome").value("Python"));
    }
    
    // --- BDD 2: Criar Curso (Cobre a criação do DTO no body) ---
    @Test
    public void deveRetornarStatus201AoCriarCurso() throws Exception {
        // Simula o service retornando o curso com ID
        when(cursoService.criarCurso(any(Curso.class))).thenReturn(curso);

        // Ação e Verificação
        mockMvc.perform(post("/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Novo\", \"categoria\": \"FINANCAS\"}"))
                .andExpect(status().isCreated()) // Cobre o ResponseEntity.created()
                .andExpect(header().string("Location", "/cursos/" + ID_CURSO)); // Cobre a URL com ID
    }

    // --- BDD 3: Filtrar por Categoria (Sucesso) ---
    @Test
    public void deveRetornarStatus200EFiltrarPorCategoria() throws Exception {
        // Simula o service retornando a lista filtrada
        when(cursoService.buscarPorCategoria(Categoria.TECNOLOGIA)).thenReturn(Arrays.asList(cursoDTO));

        // Ação e Verificação
        mockMvc.perform(get("/cursos/categoria/{categoria}", "TECNOLOGIA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria").value("TECNOLOGIA"));
    }
    
    // --- BDD 4: Filtrar por Categoria (Lista Vazia) ---
    @Test
    public void deveRetornarStatus200EListaVaziaParaCategoriaSemCursos() throws Exception {
        // Simula o service retornando lista vazia (cobre a branch de lista vazia)
        when(cursoService.buscarPorCategoria(Categoria.MARKETING)).thenReturn(Collections.emptyList());

        // Ação e Verificação
        mockMvc.perform(get("/cursos/categoria/{categoria}", "MARKETING")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}