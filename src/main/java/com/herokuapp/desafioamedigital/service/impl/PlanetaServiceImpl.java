package com.herokuapp.desafioamedigital.service.impl;

import com.herokuapp.desafioamedigital.exception.ApplicationException;
import com.herokuapp.desafioamedigital.model.adapter.PlanetaAdapter;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import com.herokuapp.desafioamedigital.repository.PlanetaRepository;
import com.herokuapp.desafioamedigital.service.PlanetaService;
import com.herokuapp.desafioamedigital.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    private final PlanetaRepository repository;

    private final SwapiService swapiService;

    @Autowired
    public PlanetaServiceImpl(PlanetaRepository repository, SwapiService swapiService) {
        this.repository = repository;
        this.swapiService = swapiService;
    }

    @Override
    public Page<PlanetaDTO> findAll(Pageable pageable, String nome) {
        Page<Planeta> entitiesPage = Optional.ofNullable(nome)
                .map(name -> this.repository.findByNome(name, pageable))
                .orElse(this.repository.findAll(pageable));

        List<PlanetaDTO> dtos = entitiesPage.getContent().stream()
                .map(PlanetaAdapter::toDTO)
                .collect(Collectors.toList());

        return new PageImpl(dtos, entitiesPage.getPageable(), entitiesPage.getTotalElements());
    }

    @Override
    public PlanetaDTO findById(Long id) {
        Planeta entity = this.repository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "not.found"));
        return PlanetaAdapter.toDTO(entity);
    }

    @Override
    public PlanetaDTO save(PlanetaDTO dto) {
        Planeta entity = PlanetaAdapter.toEntity(dto);
        Integer aparicoesEmFilmes = getAparicoesEmFilmes(dto.getNome());
        entity.setAparicoesEmFilmes(aparicoesEmFilmes);
        entity = this.repository.save(entity);
        return PlanetaAdapter.toDTO(entity);
    }

    private Integer getAparicoesEmFilmes(String nome) {
        return this.swapiService.findByName(nome)
                .map(planetDTO -> planetDTO.getFilms().size())
                .orElse(0);
    }

    @Override
    public PlanetaDTO update(Long id, PlanetaDTO dto) {
        Planeta newEntity = PlanetaAdapter.toEntity(dto);
        Planeta entity = this.repository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "not.found"));
        entity.put(newEntity);
        this.repository.save(entity);
        return PlanetaAdapter.toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}