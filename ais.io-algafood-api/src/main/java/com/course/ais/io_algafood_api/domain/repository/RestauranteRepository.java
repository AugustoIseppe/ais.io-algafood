package com.course.ais.io_algafood_api.domain.repository;

import com.course.ais.io_algafood_api.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
