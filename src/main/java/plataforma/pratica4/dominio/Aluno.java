package plataforma.pratica4.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "aluno") 
	private List<Inscricao> inscricoes = new ArrayList<>();

	public Aluno(String nome) {
		this.nome = nome;
	}

	// O 'getNome()' foi REMOVIDO (o @Data já cria)

	public void inscrever(Inscricao inscricao) {
		this.inscricoes.add(inscricao);
	}

	public double verProgresso(Curso curso) {
		return inscricoes.stream()
				.filter(inscricao -> inscricao.getCurso().equals(curso))
				.findFirst() 
				.map(Inscricao::getProgresso) 
				.map(Progresso::getValor) 
				.orElse(0.0); 
	}

	public Optional<Progresso> getProgresso(Curso curso) {
		return inscricoes.stream()
				.filter(inscricao -> inscricao.getCurso().equals(curso))
				.findFirst()
				.map(Inscricao::getProgresso);
	}
}