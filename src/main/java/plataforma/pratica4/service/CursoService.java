package plataforma.pratica4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dto.CursoDTO;
import plataforma.pratica4.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

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
        // Aqui você poderia adicionar regras de negócio antes de salvar
        // Ex: Validar se já existe um curso com o mesmo nome
        return cursoRepository.save(curso);
    }

    /**
     * Busca um curso pelo ID.
     */
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }
}
