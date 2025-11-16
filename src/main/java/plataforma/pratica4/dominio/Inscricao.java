package plataforma.pratica4.dominio;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Inscricao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // Muitas inscrições para um curso
	
	private Aluno aluno;
    private final Curso curso;
    
    @Embedded // Incorpora o Value Object Progresso
    private Progresso progresso;

    public Inscricao(Curso curso) {
        this.curso = curso;
        this.aluno = aluno;
        this.progresso = new Progresso(0.0);
    }

    public Curso getCurso() {
        return curso;
    }

    public Progresso getProgresso() {
        return progresso;
    }

    public void atualizarProgresso(Progresso novoProgresso) {
        this.progresso = novoProgresso;
    }
}