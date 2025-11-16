package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import plataforma.pratica4.dominio.Curso;
import plataforma.pratica4.dominio.Categoria;

@Data
@NoArgsConstructor

public class CursoDTO {
	private Long id;
    private String nome;
    private String categoria;
    private String instrutor;
    private int cargaHoraria;

    // Método de mapeamento, como visto em UserDTO.java do ac2_ca
    public static CursoDTO fromEntity(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setCategoria(curso.getCategoria().name()); // Converte Enum para String
        dto.setInstrutor(curso.getInstrutor());
        dto.setCargaHoraria(curso.getCargaHoraria());
        return dto;
    }
}
