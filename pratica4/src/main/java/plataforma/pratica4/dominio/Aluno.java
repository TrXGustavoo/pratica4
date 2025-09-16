package plataforma.pratica4.dominio;

//Pacote: br.com.plataforma.dominio
import java.util.ArrayList;
import java.util.List;


public class Aluno {
	private String nome;
	private List<Inscricao> inscricoes = new ArrayList<>();

	public Aluno(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void inscrever(Inscricao inscricao) {
		this.inscricoes.add(inscricao);
	}

	// Método que será o alvo do nosso teste
	public double verProgresso(Curso curso) {
//		Optional<Inscricao> inscricaoEncontrada = inscricoes.stream().filter(i -> i.getCurso().equals(curso))
//				.findFirst();
//
//		if (inscricaoEncontrada.isPresent()) {
//			return inscricaoEncontrada.get().getProgresso();
//		}
//
//		// Lança uma exceção se o aluno não estiver inscrito no curso.
//		throw new IllegalStateException("Aluno não está inscrito neste curso.");
		return 0;
	}
}