package com.course.ais.io_algafood_api.domain.service;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Cidade;
import com.course.ais.io_algafood_api.domain.model.Estado;
import com.course.ais.io_algafood_api.domain.repository.CidadeRepository;
import com.course.ais.io_algafood_api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com o código %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
    }

}
