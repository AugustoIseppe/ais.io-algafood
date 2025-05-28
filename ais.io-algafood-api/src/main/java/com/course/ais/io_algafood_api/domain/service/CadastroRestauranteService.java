package com.course.ais.io_algafood_api.domain.service;

import com.course.ais.io_algafood_api.domain.model.Restaurante;
import com.course.ais.io_algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.salvar(restaurante);
    }

}
