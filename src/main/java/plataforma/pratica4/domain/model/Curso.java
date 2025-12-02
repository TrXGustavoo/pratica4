package plataforma.pratica4.domain.model;

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
public class Curso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
	private Categoria categoria;
    
	private String nome;
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

    // Todos os 6 getters manuais foram REMOVIDOS (o @Data já cria)
    // 'public String getNome()', 'getCategoria()', 'getDescricao()', etc.

    // Método de UI, REMOVIDO da entidade de domínio
    // public boolean possuiBotaoDeAssinatura() { ... }

    // Método redundante, REMOVIDO
    // public Curso getCurso() { ... }
}