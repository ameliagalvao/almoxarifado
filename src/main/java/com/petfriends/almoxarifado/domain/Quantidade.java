package com.petfriends.almoxarifado.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Quantidade(int valor) {
    public Quantidade {
        if (valor < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
    }

    public boolean maiorOuIgual(Quantidade outra) {
        return this.valor >= outra.valor;
    }

    public Quantidade subtrair(Quantidade outra) {
        if (!maiorOuIgual(outra)) {
            throw new IllegalArgumentException("Quantidade insuficiente.");
        }
        return new Quantidade(this.valor - outra.valor);
    }
}