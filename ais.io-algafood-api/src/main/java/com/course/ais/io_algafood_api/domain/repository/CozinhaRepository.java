package com.course.ais.io_algafood_api.domain.repository;

import com.course.ais.io_algafood_api.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    List<Cozinha> findTodasByNomeContaining(String nome);
    Optional<Cozinha> findByNome(String nome);
}
