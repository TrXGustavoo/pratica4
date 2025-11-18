package plataforma.pratica4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dominio.Curso;

@DataJpaTest // Configura um banco em memória (H2) e scaneia as Entities e Repositories
@ActiveProfiles("test") // Usa o application-test.properties
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager entityManager; // Útil para inserir dados no cenário de teste

    @Test
    public void deveRetornarCursosPorCategoria() {
        // Cenário: Criar e persistir cursos
        Curso java = new Curso("Java Básico", Categoria.TECNOLOGIA);
        Curso financas = new Curso("Investimentos", Categoria.FINANCAS);
        
        entityManager.persist(java);
        entityManager.persist(financas);
        
        // Ação: Chamar o método customizado do repositório
        List<Curso> cursosTecnologia = cursoRepository.findByCategoria(Categoria.TECNOLOGIA);

        // Verificação
        assertThat(cursosTecnologia).hasSize(1);
        assertThat(cursosTecnologia.get(0).getNome()).isEqualTo("Java Básico");
    }
}