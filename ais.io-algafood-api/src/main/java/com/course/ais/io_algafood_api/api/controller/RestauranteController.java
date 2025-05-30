package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.RestauranteRepository;
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
import java.util.OptionalInt;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if (restauranteAtual.isPresent()) {
            BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formasPagamento", "endereco", "dataCadastro");
            Restaurante restauranteSalvo = cadastroRestauranteService.salvar(restauranteAtual.get());
            return ResponseEntity.ok(restauranteSalvo);
        }

        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long
                                                      restauranteId, @RequestBody Map<String, Object> campos) {

        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if (restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual.get());
        return atualizar(restauranteId, restauranteAtual.get());
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("Propriedade: " + nomePropriedade + ", Valor: " + valorPropriedade);
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (field != null) {

                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            } else {
                throw new IllegalArgumentException("Propriedade " + nomePropriedade + " não existe na classe Restaurante");
            }
        });
    }
}
