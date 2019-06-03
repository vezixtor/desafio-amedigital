package com.herokuapp.desafioamedigital.model.adapter;

import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetaAdapter {

    public static List<PlanetaDTO> toDTO(List<Planeta> entities) {
        return entities.stream().map(PlanetaAdapter::toDTO).collect(Collectors.toList());
    }

    public static PlanetaDTO toDTO(Planeta entity) {
        if (entity == null) {
            return null;
        }

        return PlanetaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .clima(entity.getClima())
                .terreno(entity.getTerreno())
                .aparicoesEmFilmes(entity.getAparicoesEmFilmes())
                .build();
    }

    public static List<Planeta> toEntity(List<PlanetaDTO> dtos) {
        return dtos.stream().map(PlanetaAdapter::toEntity).collect(Collectors.toList());
    }

    public static Planeta toEntity(PlanetaDTO dto) {
        if (dto == null) {
            return null;
        }

        return Planeta.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .clima(dto.getClima())
                .terreno(dto.getTerreno())
                .aparicoesEmFilmes(dto.getAparicoesEmFilmes())
                .build();
    }
}