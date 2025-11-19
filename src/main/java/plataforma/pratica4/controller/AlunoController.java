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

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.service.AlunoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gestão de alunos e matrículas")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar Alunos", description = "Retorna todos os alunos cadastrados (incluindo as inscrições).")
    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso.",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Aluno.class))))
    public ResponseEntity<List<Aluno>> listarTodos() {
        // Nota: Idealmente criaríamos um AlunoDTO para não expor a lista de inscrições completa aqui
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Aluno", description = "Registra um novo aluno na plataforma. Apenas o campo 'nome' é necessário.")
    @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso",
        headers = @Header(name = "Location", description = "URL do recurso criado", schema = @Schema(type = "string")),
        content = @Content(schema = @Schema(implementation = Aluno.class)))
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);
        return ResponseEntity.created(URI.create("/alunos/" + novoAluno.getId())).body(novoAluno);
    }

    @PostMapping("/{idAluno}/matricular/{idCurso}")
    @Operation(summary = "Realizar Matrícula", description = "Inscreve um aluno existente em um curso existente.")
    @ApiResponse(responseCode = "200", description = "Matrícula realizada com sucesso. Retorna o aluno atualizado.",
        content = @Content(schema = @Schema(implementation = Aluno.class)))
    @ApiResponse(responseCode = "400", description = "Aluno ou Curso não encontrados.", content = @Content)
    public ResponseEntity<Aluno> matricular(
        @Parameter(description = "ID do aluno a ser matriculado") @PathVariable Long idAluno, 
        @Parameter(description = "ID do curso para matrícula") @PathVariable Long idCurso) {
        try {
            Aluno alunoAtualizado = alunoService.matricularAluno(idAluno, idCurso);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            // Retorna 400 Bad Request se aluno ou curso não forem encontrados
            return ResponseEntity.badRequest().build(); 
        }
    }
}