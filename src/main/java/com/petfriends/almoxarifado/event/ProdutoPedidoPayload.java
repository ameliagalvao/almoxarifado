package com.petfriends.almoxarifado.event;

import java.util.UUID;

public record ProdutoPedidoPayload(
        UUID produtoId,
        int quantidade,
        LocalizacaoPayload localizacao
) {}