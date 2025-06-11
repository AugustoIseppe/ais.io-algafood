package com.course.ais.io_algafood_api.domain.exceptions;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Não existe um pedido com código %d", codigoPedido));
    }
}