package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.AlgafoodApplication;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.repository.CozinhaRepository;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class InclusaoFormaPagamentoMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository cozinhaRepository = applicationContext.getBean(FormaPagamentoRepository.class);
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao("Cartão de Crédito");
        FormaPagamento formaPagamento2 = new FormaPagamento();
        formaPagamento2.setDescricao("Cartão de Débito");

        cozinhaRepository.salvar(formaPagamento);
        cozinhaRepository.salvar(formaPagamento2);

        System.out.printf("%d - %s\n", formaPagamento.getId(), formaPagamento.getDescricao());
        System.out.printf("%d - %s\n", formaPagamento2.getId(), formaPagamento2.getDescricao());

    }
}
