package plataforma.pratica4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// 1. Carrega o contexto completo da aplicação
@SpringBootTest
// 2. Configura o MockMvc para simular as requisições HTTP
@AutoConfigureMockMvc 
class Pratica4ApplicationTests {

    @Autowired
    private MockMvc mockMvc; // Injeção do MockMvc

	/**
	 * Garante que o contexto da aplicação Spring Boot carregue corretamente.
	 * Cobre o método main e a configuração da classe.
	 */
	@Test
	void contextLoads() {
		// Este teste apenas verifica se não há exceções ao iniciar a aplicação.
	}

    /**
     * Testa o endpoint home (/) para garantir que ele retorne "Hello World".
     * Cobre o método home() e atinge 100% de Branch Coverage para ele.
     */
    @Test
    void deveRetornarHelloWorldNoEndpointRaiz() throws Exception {
        // Simula uma requisição GET para o endpoint "/"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Espera status HTTP 200
                .andExpect(content().string("Hello World")); // Espera a string exata de retorno
    }

}