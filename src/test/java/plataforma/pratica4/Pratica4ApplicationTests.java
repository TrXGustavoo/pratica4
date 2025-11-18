package plataforma.pratica4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext; 
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc 
class Pratica4ApplicationTests {

    @Autowired
    private MockMvc mockMvc; // Injeção do MockMvc

    @Autowired
    private ApplicationContext applicationContext; // Injeção para verificar o contexto

	/**
	 * Testa o carregamento do contexto da aplicação.
	 * * O uso de @SpringBootTest garante que o método main(String[] args) seja executado.
	 * Se o contexto carregar, a linha do método main é coberta.
	 */
	@Test
	void contextLoads() {
		// Verifica se o contexto Spring foi iniciado com sucesso
		assertTrue(applicationContext.getStartupDate() > 0, "O contexto da aplicação não foi carregado.");
	}

    /**
     * Testa o endpoint home (/) da aplicação.
     * Cobre o método @GetMapping("/") public String home().
     */
    @Test
    void deveRetornarHelloWorldNoEndpointRaiz() throws Exception {
        // Simula uma requisição GET para o endpoint "/"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Espera status HTTP 200
                .andExpect(content().string("Hello World")); // Espera a string exata de retorno
    }

}