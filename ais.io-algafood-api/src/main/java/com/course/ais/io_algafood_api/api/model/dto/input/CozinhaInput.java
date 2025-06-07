package com.course.ais.io_algafood_api.api.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaInput {

    @NotBlank
    private String nome;

}