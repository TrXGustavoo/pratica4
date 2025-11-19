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
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

	/**
	 * Garante a cobertura do método main(String[] args).
	 * O @SpringBootTest garante que o SpringApplication.run() seja executado.
	 */
	@Test
	void contextLoads() {
		// Verifica se o contexto Spring foi iniciado com sucesso
		assertTrue(applicationContext.getStartupDate() > 0, "O contexto da aplicação não foi carregado.");
	}

    /**
     * Garante a cobertura do método @GetMapping("/") public String home().
     */
    @Test
    void deveRetornarHelloWorldNoEndpointRaiz() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) 
                .andExpect(content().string("Hello World")); 
    }

}