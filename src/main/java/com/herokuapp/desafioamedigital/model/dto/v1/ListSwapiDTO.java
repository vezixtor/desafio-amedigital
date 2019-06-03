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
public class ListSwapiDTO {
    private String count;

    private String next;

    private String previous;

    private List<PlanetDTO> results;
}