package com.herokuapp.desafioamedigital.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nome;

    @NotNull
    private String clima;

    @NotNull
    private String terreno;

    @NotNull
    private Integer aparicoesEmFilmes;

    public void put(Planeta entity) {
        this.nome = entity.getNome();
        this.clima = entity.getClima();
        this.terreno = entity.getTerreno();
    }
}
