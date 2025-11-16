package plataforma.pratica4.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

// Entity
public class Curso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Enumerated(EnumType.STRING) // Grava o nome do Enum (TECNOLOGIA) no DB
	
	// Value Object
    
	private String nome;
    private Long id;
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
