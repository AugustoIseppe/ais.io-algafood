package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.AlgafoodApplication;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");

        cadastroCozinha.adicionar(cozinha1);
        cadastroCozinha.adicionar(cozinha2);

        System.out.printf( "%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf( "%d - %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}
