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
		
        // Refatoração: constrói a string dinamicamente
		String nomesDosCursos = cursosPublicados.stream()
				.map(Curso::getNome)
				.collect(Collectors.joining(", "));
		
		return "Cursos disponiveis: " + nomesDosCursos;
	}

	public void adicionarCurso(Curso curso) {
		this.cursosPublicados.add(curso);
	}

	public Curso selecionarCurso(Aluno aluno, Curso cursoDesejado) {
        
        return this.cursosPublicados.stream()
                .filter(curso -> curso.equals(cursoDesejado))
                .findFirst()
                .orElse(null);
	}
	
	public List<Curso> filtraPorCategoria(Categoria categoria) {
		return this.cursosPublicados.stream()
				.filter(curso -> curso.getCategoria() == categoria)
				.collect(Collectors.toList());
	}
}
