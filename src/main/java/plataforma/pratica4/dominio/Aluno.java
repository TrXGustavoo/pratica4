package plataforma.pratica4.dominio;

import java.util.ArrayList;
import java.util.List;

// Entity
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

	public double verProgresso(Curso curso) {
        // Refatoração: usa Streams para uma busca mais funcional
		return inscricoes.stream()
				.filter(inscricao -> inscricao.getCurso().equals(curso))
				.mapToDouble(Inscricao::getProgresso)
				.findFirst()
				.orElse(0.0); // Retorna 0.0 se não encontrar a inscrição
	}
}
