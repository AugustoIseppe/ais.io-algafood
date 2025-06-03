package com.course.ais.io_algafood_api.domain.exceptions;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
