package com.course.ais.io_algafood_api.api.model.mixin;

import com.course.ais.io_algafood_api.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
