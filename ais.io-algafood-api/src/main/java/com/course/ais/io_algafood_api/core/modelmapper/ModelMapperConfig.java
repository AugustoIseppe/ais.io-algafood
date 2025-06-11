package com.course.ais.io_algafood_api.core.modelmapper;

import com.course.ais.io_algafood_api.api.model.dto.input.ItemPedidoInput;
import com.course.ais.io_algafood_api.api.model.dto.output.EnderecoModel;
import com.course.ais.io_algafood_api.domain.model.Endereco;
import com.course.ais.io_algafood_api.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
        return modelMapper;
    }
}
