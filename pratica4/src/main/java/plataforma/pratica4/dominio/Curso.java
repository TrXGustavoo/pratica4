package plataforma.pratica4.dominio;

public class Curso {

    private String nome;

    private Categoria categoria;

    public Curso(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria ;
    }
    

    
    public String getNome() {
        return this.nome;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

   
    public boolean possuiBotaoDeAssinatura() {
        return true;
    }

	public Curso getCurso() {
		
		return null;
	}

}
