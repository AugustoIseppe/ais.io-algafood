package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Permissao;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.PermissaoRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroPermissaoService;
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
    public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId) {
        Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
        if (permissao.isPresent()) {
            return ResponseEntity.ok(permissao.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Permissao permissao) {
        try {
            permissao = cadastroPermissaoService.salvar(permissao);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(permissao);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

        if (permissaoAtual.isPresent()) {
            BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
            Permissao permissaoSalva = cadastroPermissaoService.salvar(permissaoAtual.get());
            return ResponseEntity.ok(permissaoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{permissaoId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long
                                                      permissaoId, @RequestBody Map<String, Object> campos) {

        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

        if (permissaoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<?> remover(@PathVariable Long permissaoId) {
        try {
            cadastroPermissaoService.excluir(permissaoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
