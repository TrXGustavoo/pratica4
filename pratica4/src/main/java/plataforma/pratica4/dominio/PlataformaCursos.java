package plataforma.pratica4.dominio;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

public class PlataformaCursos {

	private List<Curso> cursosPublicados = new ArrayList<>();
	
	public String listarCursosPara(Aluno alunoAutenticado) {
		if (cursosPublicados.isEmpty()) {
			return "Nenhum curso disponível no momento. Volte em breve!";
		}
		return "Cursos disponiveis: Curso de Java basico, curso de spring boot";
	}

	public void adicionarCurso(Curso curso) {
		this.cursosPublicados.add(curso);
	}

	public Curso selecionarCurso(Aluno aluno, Curso cursoDesejado) {
		return cursoDesejado;
	}
	
	public List<Curso> filtraPorCategoria(Categoria categoria) {
		if (this.cursosPublicados == null || this.cursosPublicados.isEmpty()) {
			return new ArrayList<>();
		}

		return this.cursosPublicados.stream()
				.filter(curso -> curso.getCategoria() == categoria)
				.collect(Collectors.toList());
	}
}