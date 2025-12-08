package com.petfriends.almoxarifado.domain;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record ProdutoPedido(
        @NonNull UUID produtoId,
        @NonNull Quantidade quantidade,
        @NonNull LocalizacaoFisica localizacao
) implements Serializable {
    public ProdutoPedido {
        if (quantidade.valor() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
    }
}