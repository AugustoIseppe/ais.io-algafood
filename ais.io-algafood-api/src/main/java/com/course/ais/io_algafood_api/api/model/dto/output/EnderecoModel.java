package com.course.ais.io_algafood_api.api.model.dto.output;

import com.course.ais.io_algafood_api.domain.model.Cidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;

}
