package plataforma.pratica4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Listar Alunos", description = "Retorna todos os alunos cadastrados.")
    public ResponseEntity<List<Aluno>> listarTodos() {
        // Nota: Idealmente criaríamos um AlunoDTO para não expor a lista de inscrições completa aqui
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Aluno", description = "Registra um novo aluno na plataforma.")
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);
        return ResponseEntity.created(URI.create("/alunos/" + novoAluno.getId())).body(novoAluno);
    }

    @PostMapping("/{idAluno}/matricular/{idCurso}")
    @Operation(summary = "Realizar Matrícula", description = "Inscreve um aluno existente em um curso existente.")
    public ResponseEntity<Aluno> matricular(@PathVariable Long idAluno, @PathVariable Long idCurso) {
        try {
            Aluno alunoAtualizado = alunoService.matricularAluno(idAluno, idCurso);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            // Retorna 400 Bad Request se aluno ou curso não forem encontrados
            return ResponseEntity.badRequest().build(); 
        }
    }
}
