package com.herokuapp.desafioamedigital.model.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDTO {
    private String name;

    private String climate;

    private String terrain;

    private String rotation_period;

    private String orbital_period;

    private String surface_water;

    private String diameter;

    private String gravity;

    private String population;

    private List<String> residents;

    private List<String> films;

    private String created;

    private String edited;

    private String url;
}