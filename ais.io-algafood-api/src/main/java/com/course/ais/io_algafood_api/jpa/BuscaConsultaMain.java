package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.AlgafoodApplication;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class BuscaConsultaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = cozinhaRepository.buscar(3L);
        if (cozinha != null) {
            System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome());
        } else {
            System.out.println("Cozinha não encontrada.");
        }

    }
}
