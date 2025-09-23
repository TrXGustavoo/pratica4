package plataforma.pratica4.dominio;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

public class PlataformaCursos {

	private List<Curso> cursosPublicados = new ArrayList<>();
	
	public String listarCursosPara(Aluno alunoAutenticado) {
		
		return null;
	}

	public void adicionarCurso(Curso cursoGamificacao) {
	}


	public Curso selecionarCurso(Aluno aluno, Curso cursoDesejado) {
		
		return null;
	}
	
	public List<Curso> filtraPorCategoria(Categoria categoria) {
		// 1. Garante que temos uma lista de cursos para trabalhar (não será nula)
		if (this.cursosPublicados == null || this.cursosPublicados.isEmpty()) {
			return new ArrayList<>(); // Retorna lista vazia se não houver cursos
		}

		// 2. Usa a API de Streams do Java para filtrar e coletar os resultados
		return this.cursosPublicados.stream()
				.filter(curso -> curso.getCategoria() == categoria)
				.collect(Collectors.toList());

		// return null
    }

}
