package com.course.ais.io_algafood_api.core.modelmapper;

import com.course.ais.io_algafood_api.api.model.dto.output.RestauranteModel;
import com.course.ais.io_algafood_api.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);
        return new ModelMapper();
    }
}
