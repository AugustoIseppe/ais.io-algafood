package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import com.course.ais.io_algafood_api.domain.repository.RestauranteRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroFormaPagamentoService;
import com.course.ais.io_algafood_api.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
        if (formaPagamento.isPresent()) {
            return ResponseEntity.ok(formaPagamento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody FormaPagamento formaPagamento) {
        try {
            formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(formaPagamento);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento restaurante) {
        Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);

        if (formaPagamentoAtual.isPresent()) {
            BeanUtils.copyProperties(restaurante, formaPagamentoAtual.get(), "id");
            FormaPagamento formaPagamentoSalvo = cadastroFormaPagamentoService.salvar(formaPagamentoAtual.get());
            return ResponseEntity.ok(formaPagamentoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{formaPagamentoId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long
                                                      formaPagamentoId, @RequestBody Map<String, Object> campos) {

        Optional<FormaPagamento> restauranteAtual = formaPagamentoRepository.findById(formaPagamentoId);

        if (restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual.get());
        return atualizar(formaPagamentoId, restauranteAtual.get());
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
    public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId) {
        try {
            cadastroFormaPagamentoService.excluir(formaPagamentoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
