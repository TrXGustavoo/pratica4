package plataforma.pratica4.dominio;

import java.util.Objects;

public final class Progresso {

    private final double valor;

    public Progresso(double valor) {
        validar(valor);
        this.valor = valor;
    }

    private void validar(double valor) {
        if (valor < 0.0 || valor > 100.0) {
            throw new IllegalArgumentException("O valor do progresso deve estar entre 0 e 100.");
        }
    }

    public double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Progresso p) {
            return Double.compare(p.valor, valor) == 0;
        }
        return false;
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