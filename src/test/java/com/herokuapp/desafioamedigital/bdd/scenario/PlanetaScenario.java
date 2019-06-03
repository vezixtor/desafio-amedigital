package com.herokuapp.desafioamedigital.bdd.scenario;

import br.com.six2six.fixturefactory.Fixture;
import com.herokuapp.desafioamedigital.bdd.scenario.common.BaseScenario;
import com.herokuapp.desafioamedigital.bdd.scenario.common.Scenario;
import com.herokuapp.desafioamedigital.model.adapter.PlanetaAdapter;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import com.herokuapp.desafioamedigital.repository.PlanetaRepository;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanetaScenario extends BaseScenario implements Scenario {

    @Autowired
    private PlanetaRepository repository;

    @Override
    public PlanetaRepository getRepository() {
        return repository;
    }

    @Override
    public Planeta gimmeEntity() {
        Planeta entity = Fixture.from(Planeta.class)
                .gimme(CommonTemplateLoader.RULE_VALID_WITHOUT_FK);
        String randomName = RandomStringUtils.random(15);
        entity.setNome(randomName);
        return entity;
    }

    @Override
    public List<Planeta> gimmeEntity(Integer quantity) {
        List<Planeta> entities = new ArrayList<>();
        while (entities.size() < quantity) {
            entities.add(gimmeEntity());
        }
        return entities;
    }

    @Override
    public Planeta gimmeEntitySaved() {
        return repository.save(gimmeEntity());
    }

    @Override
    public List<Planeta> gimmeEntitySaved(Integer quantity) {
        return gimmeEntity(quantity).stream()
                .map(entity -> repository.save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PlanetaDTO gimmeDTO() {
        return PlanetaAdapter.toDTO(gimmeEntity());
    }

    @Override
    public List<PlanetaDTO> gimmeDTO(Integer quantity) {
        return PlanetaAdapter.toDTO(gimmeEntity(quantity));
    }
}