package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.model.Estado;
import com.course.ais.io_algafood_api.domain.repository.EstadoRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroEstadoService;
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
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return cadastroEstadoService.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return cadastroEstadoService.salvar(estadoAtual);
    }

    @PatchMapping("/{estadoId}")
    public Estado atualizarParcial(@PathVariable Long
                                           estadoId, @RequestBody Map<String, Object> campos) {

        Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

        merge(campos, estadoAtual.get());
        return atualizar(estadoId, estadoAtual.get());
    }

    private static void merge(Map<String, Object> dadosOrigem, Estado estado) {

        ObjectMapper objectMapper = new ObjectMapper();
        Estado estadoOrigem = objectMapper.convertValue(dadosOrigem, Estado.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("Propriedade: " + nomePropriedade + ", Valor: " + valorPropriedade);
            Field field = ReflectionUtils.findField(Estado.class, nomePropriedade);
            if (field != null) {
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, estadoOrigem);
                ReflectionUtils.setField(field, estado, novoValor);
            } else {
                throw new IllegalArgumentException("Propriedade " + nomePropriedade + " não existe na classe Restaurante");
            }
        });
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }
}
