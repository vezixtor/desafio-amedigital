package com.herokuapp.desafioamedigital.model.adapter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;
import com.herokuapp.desafioamedigital.template.dto.PlanetaDTOTemplateLoader;
import com.herokuapp.desafioamedigital.template.entity.PlanetaTemplateLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PlanetaAdapterTest {

    @BeforeClass
    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates(CommonTemplateLoader.FIXTURE_FACTORY_BASE_PACKAGE);
    }

    @Test
    public void entityToDTO() {
        PlanetaDTO dto = Fixture.from(PlanetaDTO.class).gimme(PlanetaDTOTemplateLoader.RULE_VALID);
        Planeta entityConvert = PlanetaAdapter.toEntity(dto);
        assertThat(dto.getId(), equalTo(entityConvert.getId()));
        assertThat(dto.getNome(), equalTo(entityConvert.getNome()));
        assertThat(dto.getClima(), equalTo(entityConvert.getClima()));
        assertThat(dto.getTerreno(), equalTo(entityConvert.getTerreno()));
        assertThat(dto.getAparicoesEmFilmes(), equalTo(entityConvert.getAparicoesEmFilmes()));
    }

    @Test
    public void DTOToEntity() {
        Planeta entity = Fixture.from(Planeta.class).gimme(PlanetaTemplateLoader.RULE_VALID);
        PlanetaDTO dtoConvert = PlanetaAdapter.toDTO(entity);
        assertThat(entity.getId(), equalTo(dtoConvert.getId()));
        assertThat(entity.getNome(), equalTo(dtoConvert.getNome()));
        assertThat(entity.getClima(), equalTo(dtoConvert.getClima()));
        assertThat(entity.getTerreno(), equalTo(dtoConvert.getTerreno()));
        assertThat(entity.getAparicoesEmFilmes(), equalTo(dtoConvert.getAparicoesEmFilmes()));
    }

    @Test
    public void entityNull() {
        PlanetaDTO dto = null;
        Planeta entity = PlanetaAdapter.toEntity(dto);
        assertNull(entity);
    }

    @Test
    public void dtoNull() {
        Planeta entity = null;
        PlanetaDTO dto = PlanetaAdapter.toDTO(entity);
        assertNull(dto);
    }
}