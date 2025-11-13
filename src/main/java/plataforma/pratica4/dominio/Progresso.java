package plataforma.pratica4.dominio;

import lombok.Value;

@Value 
public final class Progresso {

    double valor;

    public Progresso(double valor) {
        if (valor < 0.0 || valor > 100.0) {
            throw new IllegalArgumentException("O valor do progresso deve estar entre 0 e 100.");
        }
        this.valor = valor;
    }

}