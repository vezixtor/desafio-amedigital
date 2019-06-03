package com.herokuapp.desafioamedigital.template.dto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.herokuapp.desafioamedigital.model.dto.v1.ListSwapiDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;

public class ListSwapiDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(ListSwapiDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("count", random(RANDOM_STRINGS));
            add("next", random(RANDOM_STRINGS));
            add("previous", random(RANDOM_STRINGS));
            add("results", has(3).of(PlanetDTO.class, RULE_VALID));
        }}).addTemplate(RULE_INVALID, new Rule() {{
        }});
    }
}
