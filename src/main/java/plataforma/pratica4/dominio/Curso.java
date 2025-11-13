package plataforma.pratica4.dominio;

public class Curso {

    private final String nome;
    private final Categoria categoria;
    private final String descricao;
    private final int cargaHoraria;
    private final double preco;
    private final String instrutor;

    public Curso(String nome, Categoria categoria) {
        this(nome, categoria, null, 0, 0.0, null);
    }

    public Curso(
            String nome,
            Categoria categoria,
            String descricao,
            int cargaHoraria,
            double preco,
            String instrutor
    ) {
        validarNome(nome);
        validarCategoria(categoria);
        validarCargaHoraria(cargaHoraria);
        validarPreco(preco);

        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.preco = preco;
        this.instrutor = instrutor;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso não pode ser vazio.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula.");
        }
    }

    private void validarCargaHoraria(int cargaHoraria) {
        if (cargaHoraria < 0) {
            throw new IllegalArgumentException("Carga horária não pode ser negativa.");
        }
    }

    private void validarPreco(double preco) {
        if (preco < 0.0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
    }

    public String getNome() {
        return nome;
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
}