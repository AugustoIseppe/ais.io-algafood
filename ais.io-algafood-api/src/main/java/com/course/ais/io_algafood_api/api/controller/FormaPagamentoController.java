package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.assembler.FormaPagamentoInputDisassembler;
import com.course.ais.io_algafood_api.api.assembler.FormaPagamentoModelAssembler;
import com.course.ais.io_algafood_api.api.model.dto.input.FormaPagamentoInput;
import com.course.ais.io_algafood_api.api.model.dto.output.FormaPagamentoModel;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.exceptions.NegocioException;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroFormaPagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PostMapping
    public FormaPagamentoModel adicionar(@RequestBody FormaPagamentoInput formaPagamento) {
        try {
            FormaPagamento formaPagamentoSalva = formaPagamentoInputDisassembler.toDomainObject(formaPagamento);
            return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamentoSalva));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException("Erro ao adicionar a forma de pagamento: " + e.getMessage());
        }
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamentoInput formaPagamento) {
        try {
            FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
            formaPagamentoInputDisassembler.copyToDomainObject(formaPagamento, formaPagamentoAtual);
            return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamentoAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException("Erro ao atualizar a forma de pagamento: " + e.getMessage());
        }
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }
}
