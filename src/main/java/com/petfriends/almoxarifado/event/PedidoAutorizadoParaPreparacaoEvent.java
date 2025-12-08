package com.petfriends.almoxarifado.event;

import java.util.List;
import java.util.UUID;

public record PedidoAutorizadoParaPreparacaoEvent(
        UUID pedidoId,
        List<ProdutoPedidoPayload> produtos
) {}

