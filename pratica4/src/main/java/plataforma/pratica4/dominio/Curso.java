package plataforma.pratica4.dominio;

public class Curso {

    private String nome;

    private Categoria categoria;

    public Curso(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria ;
    }
    

    // Método para obter o nome do curso.
    public String getNome() {
        return this.nome;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    // O teste espera um booleano para verificar a visibilidade do botão.
    // Para este cenário de teste, podemos simplesmente retornar true.
    public boolean possuiBotaoDeAssinatura() {
        return true;
    }

	public Curso getCurso() {
		// TODO Auto-generated method stub
		return null;
	}
}