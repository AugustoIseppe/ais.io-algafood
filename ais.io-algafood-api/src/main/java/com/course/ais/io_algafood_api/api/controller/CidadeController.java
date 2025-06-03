package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.exceptions.NegocioException;
import com.course.ais.io_algafood_api.domain.model.Cidade;
import com.course.ais.io_algafood_api.domain.repository.CidadeRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroCidadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cadastroCidadeService.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
        return cadastroCidadeService.salvar(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException("Erro ao adicionar a cidade: " + e.getMessage());
        }

    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cadastroCidadeService.salvar(cidadeAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException("Erro ao atualizar a cidade: " + e.getMessage());
        }
    }

    @PatchMapping("/{cidadeId}")
    public Cidade atualizarParcial(@PathVariable Long
                                           cidadeId, @RequestBody Map<String, Object> campos) {

        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        merge(campos, cidadeAtual.get());
        return atualizar(cidadeId, cidadeAtual.get());
    }

    private static void merge(Map<String, Object> dadosOrigem, Cidade cidade) {

        ObjectMapper objectMapper = new ObjectMapper();
        Cidade cidadeOrigem = objectMapper.convertValue(dadosOrigem, Cidade.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("Propriedade: " + nomePropriedade + ", Valor: " + valorPropriedade);
            Field field = ReflectionUtils.findField(Cidade.class, nomePropriedade);
            if (field != null) {

                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, cidadeOrigem);
                ReflectionUtils.setField(field, cidade, novoValor);
            } else {
                throw new IllegalArgumentException("Propriedade " + nomePropriedade + " não existe na classe Restaurante");
            }
        });
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }

}
