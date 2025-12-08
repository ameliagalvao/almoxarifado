package com.petfriends.almoxarifado.event;

import com.petfriends.almoxarifado.domain.ProdutoPedido;

import java.util.List;
import java.util.UUID;

public record PedidoAutorizadoParaPreparacaoEvent(
        UUID pedidoId,
        List<ProdutoPedido> produtos
) {}
