package plataforma.pratica4.dominio;

//Pacote: br.com.plataforma.dominio
public class Inscricao {
	private Curso curso;
	private double progresso; // Em percentual, ex: 75.5

	public Inscricao(Curso curso) {
		this.curso = curso;
		this.progresso = 0.0; // Inicia com 0%
	}

	public Curso getCurso() {
		return curso;
	}

	public double getProgresso() {
		return progresso;
	}

	public void setProgresso(double progresso) {
		this.progresso = progresso;
	}
}