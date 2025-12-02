package plataforma.pratica4.application.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor; 

import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.application.dto;
import plataforma.pratica4.repository.CursoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // GERA o construtor necessário para injetar a dependência 'final'
public class CursoService {

    // 2. Usar 'final' para garantir que a dependência seja setada apenas no construtor
    private final CursoRepository cursoRepository;

    /**
     * Retorna uma lista de todos os cursos convertidos para DTO.
     */
    public List<CursoDTO> listarTodos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(CursoDTO::fromEntity) // Usa o método estático do seu DTO
                .collect(Collectors.toList());
    }

    /**
     * Busca cursos por categoria.
     */
    public List<CursoDTO> buscarPorCategoria(Categoria categoria) {
        return cursoRepository.findByCategoria(categoria).stream()
                .map(CursoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Salva um novo curso.
     */
    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Busca um curso pelo ID.
     */
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }
}