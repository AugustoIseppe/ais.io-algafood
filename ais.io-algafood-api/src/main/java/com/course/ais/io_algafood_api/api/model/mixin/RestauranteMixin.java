package com.course.ais.io_algafood_api.api.model.mixin;

import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.model.Endereco;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

//    @JsonIgnore
    private OffsetDateTime dataCadastro;

//    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private List<Produto> produtos;

}

