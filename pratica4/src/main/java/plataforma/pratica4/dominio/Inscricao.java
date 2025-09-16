package plataforma.pratica4.dominio;


public class Inscricao {
	private Curso curso;
	private double progresso; 

	public Inscricao(Curso curso) {
		this.curso = curso;
		this.progresso = 0.0; 
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
