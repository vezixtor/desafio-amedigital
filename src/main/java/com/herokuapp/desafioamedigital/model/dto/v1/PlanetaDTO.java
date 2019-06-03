package com.herokuapp.desafioamedigital.model.dto.v1;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PlanetaDTO implements Serializable {
    private Long id;
    @NotNull
    private String nome;
    private String clima;
    private String terreno;
    private Integer aparicoesEmFilmes;
}