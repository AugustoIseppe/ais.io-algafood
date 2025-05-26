package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.AlgafoodApplication;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");

        cozinhaRepository.salvar(cozinha1);
        cozinhaRepository.salvar(cozinha2);

        System.out.printf( "%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf( "%d - %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}
