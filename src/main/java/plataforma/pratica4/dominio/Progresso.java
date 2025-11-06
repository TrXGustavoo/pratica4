package plataforma.pratica4.dominio;

import java.util.Objects;

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