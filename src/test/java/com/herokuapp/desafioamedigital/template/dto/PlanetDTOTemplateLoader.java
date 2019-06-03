package com.herokuapp.desafioamedigital.template.dto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;

import static org.assertj.core.util.Arrays.asList;

public class PlanetDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(PlanetDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("name", random(RANDOM_STRINGS));
            add("climate", random(RANDOM_STRINGS));
            add("terrain", random(RANDOM_STRINGS));
            add("rotation_period", random(RANDOM_STRINGS));
            add("orbital_period", random(RANDOM_STRINGS));
            add("surface_water", random(RANDOM_STRINGS));
            add("diameter", random(RANDOM_STRINGS));
            add("gravity", random(RANDOM_STRINGS));
            add("population", random(RANDOM_STRINGS));
            add("residents", asList(RANDOM_STRINGS));
            add("films", asList(RANDOM_STRINGS));
            add("created", random(RANDOM_STRINGS));
            add("edited", random(RANDOM_STRINGS));
            add("url", random(RANDOM_STRINGS));
        }}).addTemplate(RULE_INVALID, new Rule() {{
        }});
    }
}
