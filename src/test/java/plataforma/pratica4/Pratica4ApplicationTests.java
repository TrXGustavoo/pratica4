package plataforma.pratica4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// 1. Carrega o contexto completo da aplicação Spring Boot
@SpringBootTest
// 2. Configura o MockMvc para simular as requisições HTTP
@AutoConfigureMockMvc 
class Pratica4ApplicationTests {

    @Autowired
    private MockMvc mockMvc; // Injeção do MockMvc

	/**
	 * Testa o carregamento do contexto da aplicação.
	 * * @SpringBootTest cobre implicitamente o método main(String[] args),
	 * garantindo que a aplicação inicie sem erros.
	 */
	@Test
	void contextLoads() {
		// Este teste verifica se o Spring Application Context foi carregado.
	}

    /**
     * Testa o endpoint home (/) da aplicação.
     * * Cobre o método home() na classe Pratica4Application.java.
     */
    @Test
    void deveRetornarHelloWorldNoEndpointRaiz() throws Exception {
        // Simula uma requisição GET para o endpoint "/"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Espera status HTTP 200
                .andExpect(content().string("Hello World")); // Espera a string exata de retorno
    }

}