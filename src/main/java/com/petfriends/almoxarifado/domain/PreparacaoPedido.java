package com.petfriends.almoxarifado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Este é o agregado raíz responsável pela preparação do pedido para envio.
 * O agregado Pedido já conferiu antes com o agregado Estoque a disponibilidade
 * dos produtos e a sua localização para enviar a autorização de iniciar preparação
 * ao agregado Almoxarifado.
 * O Almoxarifado recebe um evento de Pedido com um payload completo com os dados
 * necessários para preparação, como produtos, quantidades e localização.
 */

@Entity
@Table(name = "preparacoes_pedidos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreparacaoPedido {

    @Id
    private UUID id;

    private UUID pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPreparacao status;

    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private LocalDateTime dataCancelamento;

    @ElementCollection
    @CollectionTable(name = "produtos_preparacao_pedido", joinColumns = @JoinColumn(name = "preparacao_id"))
    private List<ProdutoPedido> produtos;

    public PreparacaoPedido(UUID pedidoId, List<ProdutoPedido> produtos) {
        this.id = UUID.randomUUID();
        this.pedidoId = pedidoId;
        this.status = StatusPreparacao.AGUARDANDO;
        this.produtos = produtos;
    }


    public void iniciarPreparacao() {
        if (status != StatusPreparacao.AGUARDANDO) {
            throw new IllegalStateException("Preparação não pode ser iniciada neste estado.");
        }
        this.status = StatusPreparacao.EM_PREPARACAO;
        this.dataInicio = LocalDateTime.now();
    }

    public void concluirPreparacao() {
        if (status != StatusPreparacao.EM_PREPARACAO) {
            throw new IllegalStateException("Só é possível concluir se estiver em preparação.");
        }
        this.status = StatusPreparacao.CONCLUIDA;
        this.dataConclusao = LocalDateTime.now();
    }

    public void cancelarPreparacao() {
        if (status == StatusPreparacao.CONCLUIDA) {
            throw new IllegalStateException("Preparação já foi concluída, não pode cancelar.");
        }
        this.status = StatusPreparacao.CANCELADA;
        this.dataCancelamento = LocalDateTime.now();
    }
}