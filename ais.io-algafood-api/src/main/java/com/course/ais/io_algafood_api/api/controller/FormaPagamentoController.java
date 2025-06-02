package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Estado;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import com.course.ais.io_algafood_api.domain.repository.RestauranteRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroFormaPagamentoService;
import com.course.ais.io_algafood_api.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @GetMapping
    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamento buscar(@PathVariable Long formaPagamentoId) {
        return cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
    }

    @PostMapping
    public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
        return cadastroFormaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamento atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
        return cadastroFormaPagamentoService.salvar(formaPagamentoAtual);
    }

    @PatchMapping("/{formaPagamentoId}")
    public FormaPagamento atualizarParcial(@PathVariable Long
                                                      formaPagamentoId, @RequestBody Map<String, Object> campos) {

        Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);

        merge(campos, formaPagamentoAtual.get());
        return atualizar(formaPagamentoId, formaPagamentoAtual.get());
    }

    private static void merge(Map<String, Object> dadosOrigem, FormaPagamento formaPagamento) {

        ObjectMapper objectMapper = new ObjectMapper();
        FormaPagamento formaPagamentoOrigem = objectMapper.convertValue(dadosOrigem, FormaPagamento.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("Propriedade: " + nomePropriedade + ", Valor: " + valorPropriedade);
            Field field = ReflectionUtils.findField(FormaPagamento.class, nomePropriedade);
            if (field != null) {

                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, formaPagamentoOrigem);
                ReflectionUtils.setField(field, formaPagamento, novoValor);
            } else {
                throw new IllegalArgumentException("Propriedade " + nomePropriedade + " não existe na classe Restaurante");
            }
        });
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }
}
