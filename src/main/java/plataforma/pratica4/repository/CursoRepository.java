package plataforma.pratica4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // O Spring Data JPA cria a query automaticamente pelo nome do método!
    List<Curso> findByCategoria(Categoria categoria);
}
