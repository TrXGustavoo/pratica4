package plataforma.pratica4.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dto.CursoDTO;
import plataforma.pratica4.service.CursoService;

@WebMvcTest(CursoController.class) // Carrega apenas o contexto Web p/ este controller
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula chamadas HTTP

    @MockBean // Substitui o Bean real do Service por um Mock no contexto do Spring
    private CursoService cursoService;

    @Test
    public void deveRetornarStatus200eListaDeCursos() throws Exception {
        // Cenário
        Curso curso = new Curso("Python", Categoria.TECNOLOGIA);
        CursoDTO dto = CursoDTO.fromEntity(curso);
        
        // Mockamos a resposta do serviço
        when(cursoService.listarTodos()).thenReturn(Arrays.asList(dto));

        // Ação e Verificação
        mockMvc.perform(get("/cursos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera HTTP 200
                .andExpect(jsonPath("$[0].nome").value("Python")) // Verifica JSON
                .andExpect(jsonPath("$[0].categoria").value("TECNOLOGIA"));
    }
}