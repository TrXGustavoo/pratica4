package plataforma.pratica4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.headers.Header; 
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;
import plataforma.pratica4.application.dto.CursoDTO;
import plataforma.pratica4.application.service.CursoService;

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
    @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso", 
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = CursoDTO.class))))
    public ResponseEntity<List<CursoDTO>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar por Categoria", description = "Busca cursos de uma categoria específica.")
    @ApiResponse(responseCode = "200", description = "Lista de cursos filtrada.",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = CursoDTO.class))))
    public ResponseEntity<List<CursoDTO>> buscarPorCategoria(
        @Parameter(description = "Categoria do curso. Valores permitidos: TECNOLOGIA, FINANCAS, MARKETING", 
                   schema = @Schema(allowableValues = {"TECNOLOGIA", "FINANCAS", "MARKETING"}))
        @PathVariable Categoria categoria) {
        return ResponseEntity.ok(cursoService.buscarPorCategoria(categoria));
    }

    @PostMapping
    @Operation(summary = "Criar Curso", description = "Adiciona um novo curso à plataforma.")
    @ApiResponse(responseCode = "201", description = "Curso criado com sucesso", 
        headers = @Header(name = "Location", description = "URL do recurso criado", schema = @Schema(type = "string")),
        content = @Content(schema = @Schema(implementation = CursoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida (Ex: nome não informado)", content = @Content)
    public ResponseEntity<CursoDTO> criarCurso(@RequestBody Curso curso) {
        // Em um cenário real, receberíamos um CursoForm/InputDTO aqui
        Curso novoCurso = cursoService.criarCurso(curso);
        
        // Retorna 201 Created e o DTO do curso criado
        return ResponseEntity
                .created(URI.create("/cursos/" + novoCurso.getId()))
                .body(CursoDTO.fromEntity(novoCurso));
    }
}