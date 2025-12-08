package com.petfriends.almoxarifado.domain;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

import java.io.Serializable;

@Embeddable
public record Localizacao(
        @NonNull String setor,
        @NonNull String prateleira,
        @NonNull String corredor
) implements Serializable {
    public Localizacao {
        if (setor.isBlank() || prateleira.isBlank() || corredor.isBlank()) {
            throw new IllegalArgumentException("Localização deve ser completa.");
        }
    }

    @Override
    public String toString() {
        return String.format("Setor %s - Corredor %s - Prateleira %s", setor, corredor, prateleira);
    }
}
