package plataforma.pratica4.dominio;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable // 1. Para o JPA embutir na entidade (ex: Inscricao) 
@Getter // 2. Gera o getValor() 
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) // 3. Necessário para o JPA 
@EqualsAndHashCode // 4. Para a lógica de Value Object [cite: 113, 114, 125]

public final class Progresso {

    private final double valor;

    public Progresso(double valor) {
        if (valor < 0.0 || valor > 100.0) {
            throw new IllegalArgumentException("O valor do progresso deve estar entre 0 e 100.");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progresso progresso = (Progresso) o;
        return Double.compare(progresso.valor, valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor + "%";
    }
}