package com.course.ais.io_algafood_api.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder //  O bulder é usado para criar instâncias de Problema de forma mais legível e flexível
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
    private List<Field> fields;


    @Builder
    @Getter
    public static class Field {
        private String name;
        private String userMessage;
    }
}
