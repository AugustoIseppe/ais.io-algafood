package com.course.ais.io_algafood_api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public FormaPagamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com o código %d", formaPagamentoId));
    }

}
