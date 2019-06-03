package com.herokuapp.desafioamedigital.template;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

public interface CommonTemplateLoader extends TemplateLoader {
    String FIXTURE_FACTORY_BASE_PACKAGE = "com.herokuapp.desafioamedigital.template";
    String RULE_VALID = "RULE_VALID";
    String RULE_INVALID = "RULE_INVALID";
    String RULE_VALID_WITHOUT_ID = "RULE_VALID_WITHOUT_ID";
    String RULE_VALID_WITHOUT_FK = "RULE_VALID_WITHOUT_FK";
    Object[] RANDOM_STRINGS = {"Lorem", "ipsum", "dolor", "sit", "amet"};
    Object[] RANDOM_CHARACTERES = {'A', 'B', 'C', 'D', 'E'};
}
