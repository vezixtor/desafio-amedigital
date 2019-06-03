package com.herokuapp.desafioamedigital.template.dto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;

import static org.apache.commons.lang.math.NumberUtils.LONG_ONE;

public class PlanetaDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(PlanetaDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("id", random(Long.class, range(LONG_ONE, Long.MAX_VALUE)));
            add("nome", random(RANDOM_STRINGS));
            add("clima", random(RANDOM_STRINGS));
            add("terreno", random(RANDOM_STRINGS));
            add("aparicoesEmFilmes", random(0, 1, 2, 3));
        }}).addTemplate(RULE_INVALID, new Rule() {{
        }});
    }
}
