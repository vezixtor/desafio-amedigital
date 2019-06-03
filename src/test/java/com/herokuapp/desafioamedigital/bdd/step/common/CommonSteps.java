package com.herokuapp.desafioamedigital.bdd.step.common;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.springframework.boot.web.server.LocalServerPort;

public class CommonSteps {

    @LocalServerPort
    private int serverPort;

    @Before
    public void loadTemplatesAndSetRestAssured() {
        FixtureFactoryLoader.loadTemplates(CommonTemplateLoader.FIXTURE_FACTORY_BASE_PACKAGE);

        RestAssured.port = serverPort;
        RestAssured.defaultParser = Parser.JSON;
    }
}