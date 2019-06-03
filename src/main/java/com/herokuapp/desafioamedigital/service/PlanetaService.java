package com.herokuapp.desafioamedigital.service;

import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanetaService {

    Page<PlanetaDTO> findAll(Pageable pageable, String nome);

    PlanetaDTO findById(Long id);

    PlanetaDTO save(PlanetaDTO dto);

    PlanetaDTO update(Long id, PlanetaDTO dto);

    void delete(Long id);
}