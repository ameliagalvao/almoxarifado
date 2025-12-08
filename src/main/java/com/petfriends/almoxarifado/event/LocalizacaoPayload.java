package com.petfriends.almoxarifado.event;


public record LocalizacaoPayload(
        String setor,
        String prateleira,
        String corredor
) {}