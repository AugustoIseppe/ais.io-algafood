package com.course.ais.io_algafood_api.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
 public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    // Classe para serializar objetos do tipo Page<T> em JSON (usada para alterar o formato de saída da paginação)
    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        gen.writeStartObject();

        gen.writeObjectField( "content", page.getContent());
        gen.writeNumberField("size", page.getSize());
        gen.writeNumberField("totalElements", page.getTotalElements());
        gen.writeNumberField("totalPages", page.getTotalPages());
        gen.writeNumberField("number", page.getNumber());

        gen.writeEndObject();
    }
}
