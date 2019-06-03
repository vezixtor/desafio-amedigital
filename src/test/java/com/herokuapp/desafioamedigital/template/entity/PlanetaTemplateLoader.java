package com.herokuapp.desafioamedigital.template.entity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;

import static org.apache.commons.lang.math.NumberUtils.LONG_ONE;

public class PlanetaTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(Planeta.class).addTemplate(RULE_VALID, new Rule(){{
            add("id", uniqueRandom(LONG_ONE, Long.MAX_VALUE));
            add("nome", random(RANDOM_STRINGS));
            add("clima", random(RANDOM_STRINGS));
            add("terreno", random(RANDOM_STRINGS));
            add("aparicoesEmFilmes", random(0, 1, 2, 3));
        }}).addTemplate(RULE_VALID_WITHOUT_ID).inherits(RULE_VALID, new Rule() {{
            add("id", null);
        }}).addTemplate(RULE_VALID_WITHOUT_FK).inherits(RULE_VALID_WITHOUT_ID, new Rule() {{
        }}).addTemplate(RULE_INVALID, new Rule(){{
        }});
    }
}
