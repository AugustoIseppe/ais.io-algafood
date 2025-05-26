package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.AlgafoodApplication;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AlteracaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L); // Supondo que a cozinha com ID 1 já exista
        cozinha.setNome("Brasileira - Atualizada");
        cadastroCozinha.adicionar(cozinha);

        System.out.printf( "%d - %s\n", cozinha.getId(), cozinha.getNome());

    }
}
