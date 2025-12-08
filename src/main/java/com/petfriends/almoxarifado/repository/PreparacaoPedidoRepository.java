package com.petfriends.almoxarifado.repository;

import com.petfriends.almoxarifado.domain.PreparacaoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PreparacaoPedidoRepository extends JpaRepository<PreparacaoPedido, UUID> {
    Optional<PreparacaoPedido> findByPedidoId(UUID pedidoId);
}