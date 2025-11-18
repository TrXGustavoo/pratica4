package plataforma.pratica4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API da Plataforma de Cursos - Prática 4")
                        .version("1.0.0")
                        .description("Documentação dos endpoints da API de gestão de alunos e cursos.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Suporte da Plataforma")
                                .email("suporte@plataforma.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
