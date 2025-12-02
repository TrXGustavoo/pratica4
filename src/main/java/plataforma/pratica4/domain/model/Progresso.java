package plataforma.pratica4.domain.model;

//import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable 
@Getter 
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) 
@EqualsAndHashCode 
public final class Progresso {

    private double valor;

    public Progresso(double valor) {
        if (valor < 0.0 || valor > 100.0) {
            throw new IllegalArgumentException("O valor do progresso deve estar entre 0 e 100.");
        }
        this.valor = valor;
    }

    // O 'getValor()' foi REMOVIDO (o @Getter já cria)
    // O 'equals(Object o)' foi REMOVIDO (o @EqualsAndHashCode já cria)
    // O 'hashCode()' foi REMOVIDO (o @EqualsAndHashCode já cria)

    @Override
    public String toString() {
        return valor + "%";
    }
}
