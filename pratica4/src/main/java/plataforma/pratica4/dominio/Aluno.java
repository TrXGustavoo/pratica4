package plataforma.pratica4.dominio;

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

	public double verProgresso(Curso curso) {
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				return inscricao.getProgresso();
			}
		}
		return 0;
	}
}