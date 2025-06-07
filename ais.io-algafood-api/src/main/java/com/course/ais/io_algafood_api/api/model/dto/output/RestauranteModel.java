package com.course.ais.io_algafood_api.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
}
