package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.model.dto.input.RestauranteInputDTO;
import com.course.ais.io_algafood_api.api.model.dto.output.CozinhaOutputDTO;
import com.course.ais.io_algafood_api.api.model.dto.output.RestauranteOutputDTO;
import com.course.ais.io_algafood_api.domain.exceptions.CozinhaNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.exceptions.NegocioException;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.RestauranteRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<RestauranteOutputDTO> listar() {
        return toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteOutputDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return toModel(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteOutputDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
        try {
            Restaurante restaurante = toDomainObject(restauranteInputDTO);
            return toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException("Erro ao adicionar o restaurante: " + e.getMessage());
        }

    }

    @PutMapping("/{restauranteId}")
    public RestauranteOutputDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
        try {
            Restaurante restaurante = toDomainObject(restauranteInputDTO);
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            return toModel(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException("Erro ao atualizar o restaurante: " + e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId) {
        cadastroRestauranteService.excluir(restauranteId);
    }


    private static RestauranteOutputDTO toModel(Restaurante restaurante) {
        CozinhaOutputDTO cozinhaDTO = new CozinhaOutputDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteOutputDTO restauranteOutputDTO = new RestauranteOutputDTO();
        restauranteOutputDTO.setId(restaurante.getId());
        restauranteOutputDTO.setNome(restaurante.getNome());
        restauranteOutputDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteOutputDTO.setCozinha(cozinhaDTO);
        return restauranteOutputDTO;
    }

    private List<RestauranteOutputDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

    private Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInputDTO.getNome());
        restaurante.setTaxaFrete(restauranteInputDTO.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInputDTO.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
