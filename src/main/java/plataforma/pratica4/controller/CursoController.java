package plataforma.pratica4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.dto.CursoDTO;
import plataforma.pratica4.service.CursoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Gerenciamento do catálogo de cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna a lista de todos os cursos disponíveis formatados como DTO.")
    public ResponseEntity<List<CursoDTO>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar por Categoria", description = "Busca cursos de uma categoria específica (TECNOLOGIA, FINANCAS, MARKETING).")
    public ResponseEntity<List<CursoDTO>> buscarPorCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(cursoService.buscarPorCategoria(categoria));
    }

    @PostMapping
    @Operation(summary = "Criar Curso", description = "Adiciona um novo curso à plataforma.")
    public ResponseEntity<CursoDTO> criarCurso(@RequestBody Curso curso) {
        // Em um cenário real, receberíamos um CursoForm/InputDTO aqui
        Curso novoCurso = cursoService.criarCurso(curso);
        
        // Retorna 201 Created e o DTO do curso criado
        return ResponseEntity
                .created(URI.create("/cursos/" + novoCurso.getId()))
                .body(CursoDTO.fromEntity(novoCurso));
    }
}
