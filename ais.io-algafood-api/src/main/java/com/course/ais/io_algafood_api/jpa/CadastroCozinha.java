package com.course.ais.io_algafood_api.jpa;

import com.course.ais.io_algafood_api.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        if (cozinha != null) {
            manager.remove(cozinha);
        }
    }
}
