package plataforma.pratica4.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plataforma.pratica4.dominio.Aluno;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Inscricao;
import plataforma.pratica4.repository.AlunoRepository;
import plataforma.pratica4.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    
    private final CursoRepository cursoRepository; // Necessário para buscar o curso na inscrição

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    /**
     * Regra de Negócio: Inscrever um aluno em um curso.
     */
    public Aluno matricularAluno(Long idAluno, Long idCurso) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(idAluno);
        Optional<Curso> cursoOpt = cursoRepository.findById(idCurso);

        if (alunoOpt.isPresent() && cursoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            Curso curso = cursoOpt.get();

            // Cria a inscrição (usando o construtor que corrigimos)
            Inscricao novaInscricao = new Inscricao(aluno, curso);
            
            // Adiciona na lista do aluno
            aluno.inscrever(novaInscricao);

            // Salva o aluno (o Cascade do JPA deve salvar a inscrição se configurado, 
            // ou você precisaria de um InscricaoRepository)
            return alunoRepository.save(aluno);
        }
        
        throw new RuntimeException("Aluno ou Curso não encontrados.");
    }
}
