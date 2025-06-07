package com.course.ais.io_algafood_api.core.jackson;

import com.course.ais.io_algafood_api.api.model.mixin.CidadeMixin;
import com.course.ais.io_algafood_api.api.model.mixin.CozinhaMixin;
import com.course.ais.io_algafood_api.domain.model.Cidade;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(
                Cozinha.class,
                CozinhaMixin.class);
        setMixInAnnotation(
                Cidade.class,
                CidadeMixin.class
        );
    }
}
