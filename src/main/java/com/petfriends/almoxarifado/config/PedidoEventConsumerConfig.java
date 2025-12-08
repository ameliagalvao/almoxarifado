package com.petfriends.almoxarifado.config;

import com.petfriends.almoxarifado.event.PedidoAutorizadoParaPreparacaoEvent;
import com.petfriends.almoxarifado.service.PreparacaoPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PedidoEventConsumerConfig {

    @Bean
    public Consumer<PedidoAutorizadoParaPreparacaoEvent> consumer(PreparacaoPedidoService service) {
        return service::processar;
    }

}
