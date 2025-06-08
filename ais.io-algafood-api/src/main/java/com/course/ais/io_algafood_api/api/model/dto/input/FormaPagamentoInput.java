package com.course.ais.io_algafood_api.api.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;
}
