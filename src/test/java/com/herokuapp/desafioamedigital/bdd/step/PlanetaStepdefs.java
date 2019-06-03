package com.herokuapp.desafioamedigital.bdd.step;

import com.herokuapp.desafioamedigital.bdd.scenario.PlanetaScenario;
import com.herokuapp.desafioamedigital.bdd.step.common.FeatureTest;
import com.herokuapp.desafioamedigital.model.adapter.PlanetaAdapter;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

public class PlanetaStepdefs extends FeatureTest implements En {
    private final String ENDPOINT = "/v1/planeta/";
    private List<Planeta> entities = new ArrayList<>();
    private List<PlanetaDTO> dtos = new ArrayList<>();
    private Response response;
    private PlanetaDTO dto;

    @Autowired
    private PlanetaScenario scenario;

    public PlanetaStepdefs() {
        Given("^(\\d+) registro\\(s\\) de planeta preexistente$", (Integer quantity) -> {
            this.entities = scenario.gimmeEntitySaved(quantity);
            this.dtos = PlanetaAdapter.toDTO(this.entities);
        });

        When("^consulto a lista de planeta$", () -> {
            this.response = RestAssured.expect()
                    .statusCode(200)
                    .body(CONTENT_PATH, hasSize(entities.size()))
                    .when()
                    .get(ENDPOINT);

            List<PlanetaDTO> dtos = response.jsonPath().getList(CONTENT_PATH, PlanetaDTO.class);
            this.dtos.forEach(dto -> assertThat(dtos, hasItem(dto)));
        });

        When("^tento obter os dados de planeta pela chave$", () -> {
            this.dto = dtos.get(0);
            Long id = dto.getId();

            this.response = RestAssured.expect()
                    .statusCode(200)
                    .when()
                    .get(ENDPOINT + id);
        });

        When("^tento cadastrar os dados de planeta$", () -> {
            this.dto = scenario.gimmeDTO();

            this.response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(this.dto)
                    .expect()
                    .statusCode(201)
                    .when()
                    .post(ENDPOINT);

            PlanetaDTO responseDTO = response.getBody().as(PlanetaDTO.class);
            assertNotNull(responseDTO);
            this.dto.setId(responseDTO.getId());
        });

        When("^tento alterar os dados de planeta pela chave$", () -> {
            this.dto = scenario.gimmeDTO();
            Long id = this.dtos.get(0).getId();

            this.response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(this.dto)
                    .expect()
                    .statusCode(200)
                    .when()
                    .put(ENDPOINT + id);

            this.dto.setId(id);
        });

        When("^tento deletar o registro de planeta pela chave$", () -> {
            Long id = this.dtos.get(0).getId();

            RestAssured.expect()
                    .statusCode(200)
                    .when()
                    .delete(ENDPOINT + id);
        });

        Then("^o serviço me retorna a planeta$", () -> {
            PlanetaDTO responseDTO = this.response.getBody().as(PlanetaDTO.class);
            assertNotNull(responseDTO);
            assertThat(this.dto, equalTo(responseDTO));
        });

        Then("^o serviço remove o registro de planeta$", () -> {
            Long id = this.dtos.get(0).getId();

            RestAssured.given()
                    .expect()
                    .statusCode(404)
                    .when()
                    .get(ENDPOINT + id);
        });

        Then("^o serviço me retorna uma lista de planeta$", () -> {
            List<PlanetaDTO> dtos = this.response.jsonPath().getList(CONTENT_PATH, PlanetaDTO.class);
            this.dtos.forEach(dto -> assertThat(dtos, hasItem(dto)));
        });

        After(new String[]{"@Planeta"}, (Scenario scenario) -> {
            this.scenario.deleteAll();
            assertTrue("Base suja", this.scenario.isClean());
        });
    }
}