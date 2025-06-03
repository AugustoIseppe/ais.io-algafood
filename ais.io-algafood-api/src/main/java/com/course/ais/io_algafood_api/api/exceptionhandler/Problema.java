package com.course.ais.io_algafood_api.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder //  O bulder é usado para criar instâncias de Problema de forma mais legível e flexível
public class Problema {
    private LocalDateTime dataHora;
    private String mensagem;
}
