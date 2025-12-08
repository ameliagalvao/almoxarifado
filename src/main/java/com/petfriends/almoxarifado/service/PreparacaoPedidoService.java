package com.petfriends.almoxarifado.service;

import com.petfriends.almoxarifado.domain.PreparacaoPedido;
import com.petfriends.almoxarifado.repository.PreparacaoPedidoRepository;
import com.petfriends.almoxarifado.event.PedidoAutorizadoParaPreparacaoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PreparacaoPedidoService {

    private final PreparacaoPedidoRepository repository;

    @Transactional
    public void processar(PedidoAutorizadoParaPreparacaoEvent event) {
        // 1. Criar o agregado a partir do pedidoId e produtos do evento
        PreparacaoPedido preparacao = new PreparacaoPedido(
                event.pedidoId(),
                event.produtos()
        );

        // 2. Invocar a regra de negócio de mudança de estado
        preparacao.iniciarPreparacao();

        // 3. Persistir o novo agregado com status atualizado
        repository.save(preparacao);
    }
}