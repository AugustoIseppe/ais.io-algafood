package com.course.ais.io_algafood_api.api.model;

import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhaXmlWrapper {

    @JsonProperty()
    @NonNull
    List<Cozinha> cozinhas;
}
