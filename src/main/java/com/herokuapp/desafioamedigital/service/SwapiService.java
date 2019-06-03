package com.herokuapp.desafioamedigital.service;

import com.herokuapp.desafioamedigital.model.dto.v1.ListSwapiDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;

import java.util.Optional;

public interface SwapiService {

    ListSwapiDTO findAllPlanets(String search);

    Optional<PlanetDTO> findByName(String name);

    ListSwapiDTO findAllSwapiPlanets(Integer page, String search);
}