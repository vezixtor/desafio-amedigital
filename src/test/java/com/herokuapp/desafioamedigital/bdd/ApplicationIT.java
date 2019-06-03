package com.herokuapp.desafioamedigital.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.herokuapp.desafioamedigital.bdd.step", "com.herokuapp.desafioamedigital.bdd.step.common"},
        features = "src/test/resources/feature"
)
public class ApplicationIT {
}