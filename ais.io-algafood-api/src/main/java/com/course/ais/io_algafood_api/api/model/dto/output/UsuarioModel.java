package com.course.ais.io_algafood_api.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
}