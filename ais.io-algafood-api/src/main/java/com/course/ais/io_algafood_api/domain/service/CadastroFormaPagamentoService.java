package com.course.ais.io_algafood_api.domain.service;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.model.Permissao;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import com.course.ais.io_algafood_api.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroFormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public void excluir(Long formaPagamentoId) {
        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Forma de Pagamento de código %d não encontrada", formaPagamentoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Forma de Pagamento de código %d não pode ser removida, pois está em uso", formaPagamentoId));
        }
    }
}
