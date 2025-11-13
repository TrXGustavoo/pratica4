package plataforma.pratica4.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor 
public class Curso {
	
    private String nome;
    private Categoria categoria;
    private String descricao;
    private int cargaHoraria;
    private double preco;
    private String instrutor;

}