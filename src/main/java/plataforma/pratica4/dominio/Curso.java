package plataforma.pratica4.dominio;

// Entity
public class Curso {

    private String nome;
    private Categoria categoria;
    private String descricao;
    private int cargaHoraria;
    private double preco;
    private String instrutor;

    public Curso(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria ;
    }

    public Curso(String nome, Categoria categoria, String descricao, int cargaHoraria, double preco, String instrutor) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.preco = preco;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return this.nome;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public double getPreco() {
        return preco;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public boolean possuiBotaoDeAssinatura() {
        return true;
    }

    public Curso getCurso() {
		return this; 
	}

}
