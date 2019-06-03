package com.herokuapp.desafioamedigital.bdd.scenario.common;

public interface Scenario {
    Object getRepository();

    Object gimmeEntity();

    Object gimmeEntity(Integer quantity);

    Object gimmeEntitySaved();

    Object gimmeEntitySaved(Integer quantity);

    Object gimmeDTO();

    Object gimmeDTO(Integer quantity);
}
