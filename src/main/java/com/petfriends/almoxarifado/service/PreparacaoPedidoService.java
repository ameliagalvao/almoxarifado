package com.petfriends.almoxarifado.service;

import com.petfriends.almoxarifado.domain.Localizacao;
import com.petfriends.almoxarifado.domain.PreparacaoPedido;
import com.petfriends.almoxarifado.domain.ProdutoPedido;
import com.petfriends.almoxarifado.domain.Quantidade;
import com.petfriends.almoxarifado.repository.PreparacaoPedidoRepository;
import com.petfriends.almoxarifado.event.PedidoAutorizadoParaPreparacaoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreparacaoPedidoService {

    private final PreparacaoPedidoRepository repository;

    @Transactional
    public void processar(PedidoAutorizadoParaPreparacaoEvent event) {
        List<ProdutoPedido> produtos = event.produtos().stream()
                .map(p -> new ProdutoPedido(
                        p.produtoId(),
                        new Quantidade(p.quantidade()),
                        new Localizacao(p.localizacao().setor(), p.localizacao().prateleira(), p.localizacao().corredor())
                )).toList();

        PreparacaoPedido preparacao = new PreparacaoPedido(event.pedidoId(), produtos);
        preparacao.iniciarPreparacao();
        repository.save(preparacao);
    }
}