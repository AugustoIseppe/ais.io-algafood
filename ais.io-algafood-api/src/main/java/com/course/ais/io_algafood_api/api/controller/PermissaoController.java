package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Permissao;
import com.course.ais.io_algafood_api.domain.repository.PermissaoRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroPermissaoService;
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
@RequestMapping("/permissao")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    @GetMapping("/{permissaoId}")
    public Permissao buscar(@PathVariable Long permissaoId) {
        return cadastroPermissaoService.buscarOuFalhar(permissaoId);
    }

    @PostMapping
    public Permissao adicionar(@RequestBody Permissao permissao) {
       return cadastroPermissaoService.salvar(permissao);
    }

    @PutMapping("/{permissaoId}")
    public Permissao atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Permissao permissaoAtual = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        BeanUtils.copyProperties(permissao, permissaoAtual, "id");
        return cadastroPermissaoService.salvar(permissaoAtual);
    }

    @PatchMapping("/{permissaoId}")
    public Permissao atualizarParcial(@PathVariable Long
                                                      permissaoId, @RequestBody Map<String, Object> campos) {

        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

        merge(campos, permissaoAtual.get());
        return atualizar(permissaoId, permissaoAtual.get());
    }

    private static void merge(Map<String, Object> dadosOrigem, Permissao permissaoDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Permissao permissaoOrigem = objectMapper.convertValue(dadosOrigem, Permissao.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("Propriedade: " + nomePropriedade + ", Valor: " + valorPropriedade);
            Field field = ReflectionUtils.findField(Permissao.class, nomePropriedade);
            if (field != null) {

                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, permissaoOrigem);
                ReflectionUtils.setField(field, permissaoDestino, novoValor);
            } else {
                throw new IllegalArgumentException("Propriedade " + nomePropriedade + " não existe na classe Restaurante");
            }
        });
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        cadastroPermissaoService.excluir(permissaoId);
    }

}
