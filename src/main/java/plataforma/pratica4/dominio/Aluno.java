package plataforma.pratica4.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Entity
public class Aluno {
	
	// Value Object 
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
		// Correção aqui:
		// 1. Primeiro, usamos .map() para obter o objeto Progresso da inscrição.
		// 2. Depois, usamos .mapToDouble() para extrair o valor double de dentro do objeto Progresso.
		return inscricoes.stream()
				.filter(inscricao -> inscricao.getCurso().equals(curso))
				.findFirst() // Encontra a inscrição primeiro
				.map(Inscricao::getProgresso) // Mapeia para o objeto Progresso
				.map(Progresso::getValor) // Extrai o valor double do progresso
				.orElse(0.0); // Retorna 0.0 se não encontrar a inscrição
	}

	public Optional<Progresso> getProgresso(Curso curso) {
		return inscricoes.stream()
				.filter(inscricao -> inscricao.getCurso().equals(curso))
				.findFirst()
				.map(Inscricao::getProgresso);
	}
}