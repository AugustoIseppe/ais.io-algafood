package com.course.ais.io_algafood_api.infrastructure.repository;

import com.course.ais.io_algafood_api.domain.model.FormaPagamento;
import com.course.ais.io_algafood_api.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        TypedQuery<FormaPagamento> query = manager.createQuery("from FormaPagamento", FormaPagamento.class);
        return query.getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        if (formaPagamento != null) {
            manager.remove(formaPagamento);
        }
    }
}
