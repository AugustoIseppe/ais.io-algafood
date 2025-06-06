package com.course.ais.io_algafood_api.api.model.mixin;

import com.course.ais.io_algafood_api.domain.model.Estado;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class CidadeMixin {

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Estado estado;

}
