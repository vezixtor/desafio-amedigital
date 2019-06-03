package com.herokuapp.desafioamedigital.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.herokuapp.desafioamedigital.model.adapter.PlanetaAdapter;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.model.entity.Planeta;
import com.herokuapp.desafioamedigital.repository.PlanetaRepository;
import com.herokuapp.desafioamedigital.service.SwapiService;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

public class PlanetaServiceImplTest {
    @InjectMocks
    private PlanetaServiceImpl service;

    @Mock
    private PlanetaRepository repository;

    @Mock
    private SwapiService swapiService;

    @BeforeClass
    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates(CommonTemplateLoader.FIXTURE_FACTORY_BASE_PACKAGE);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() {
        PlanetaDTO dto = Fixture.from(PlanetaDTO.class).gimme(CommonTemplateLoader.RULE_VALID);

        PlanetDTO planetDTO = Fixture.from(PlanetDTO.class).gimme(CommonTemplateLoader.RULE_VALID);
        doReturn(Optional.ofNullable(planetDTO)).when(this.swapiService).findByName(dto.getNome());

        Planeta planeta = PlanetaAdapter.toEntity(dto);
        int aparicoesEmFilmes = planetDTO.getFilms().size();
        planeta.setAparicoesEmFilmes(aparicoesEmFilmes);
        doReturn(planeta).when(this.repository).save(planeta);

        PlanetaDTO save = service.save(dto);
        assertThat(aparicoesEmFilmes, equalTo(save.getAparicoesEmFilmes()));
    }
}