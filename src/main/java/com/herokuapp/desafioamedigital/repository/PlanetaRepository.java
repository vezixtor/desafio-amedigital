package com.herokuapp.desafioamedigital.repository;

import com.herokuapp.desafioamedigital.model.entity.Planeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

    Page<Planeta> findByNome(String lastname, Pageable pageable);
}
