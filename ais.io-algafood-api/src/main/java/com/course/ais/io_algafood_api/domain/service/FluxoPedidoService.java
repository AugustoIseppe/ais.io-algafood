package com.course.ais.io_algafood_api.domain.service;

import com.course.ais.io_algafood_api.domain.exceptions.NegocioException;
import com.course.ais.io_algafood_api.domain.model.Pedido;
import com.course.ais.io_algafood_api.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
                    pedido.getId(),
                    pedido.getStatus().getDescricao(),
                    StatusPedido.CONFIRMADO.getDescricao()
            ));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

}
