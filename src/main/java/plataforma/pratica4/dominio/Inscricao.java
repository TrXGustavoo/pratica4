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
	
	@ManyToOne
	private Aluno aluno;
    
    private Curso curso;
    
    @Embedded
    private Progresso progresso;

    public Inscricao(Aluno aluno, Curso curso) {
        this.aluno = aluno; // Corrigido para receber Aluno
        this.curso = curso;
        this.progresso = new Progresso(0.0);
    }

    public void atualizarProgresso(Progresso novoProgresso) {
        this.progresso = novoProgresso;
    }
}