package com.course.ais.io_algafood_api.domain.repository;

import com.course.ais.io_algafood_api.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
