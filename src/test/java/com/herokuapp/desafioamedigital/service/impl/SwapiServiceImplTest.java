package com.herokuapp.desafioamedigital.service.impl;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.herokuapp.desafioamedigital.feign.SwapiClient;
import com.herokuapp.desafioamedigital.template.CommonTemplateLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

public class SwapiServiceImplTest {
    @InjectMocks
    private SwapiServiceImpl service;

    @Mock
    private SwapiClient swapiClient;

    @Mock
    private HttpServletRequest request;

    @BeforeClass
    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates(CommonTemplateLoader.FIXTURE_FACTORY_BASE_PACKAGE);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void putRequestURL_page() {
        String url = "http://localhost:8080/v1/planeta/";
        doReturn(new StringBuffer(url)).when(this.request).getRequestURL();

        String swapiUrl = "https://swapi.co/api/planets/?page=2";
        String requestURL = ReflectionTestUtils.invokeMethod(service, "putRequestURL", swapiUrl);

        String expect = swapiUrl.replaceAll("https://swapi.co/api/planets/", url);
        assertThat(expect, equalTo(requestURL));
    }

    @Test
    public void putRequestURL_page_search() {
        String url = "http://localhost:8080/v1/planeta/";
        doReturn(new StringBuffer(url)).when(this.request).getRequestURL();

        String swapiUrl = "https://swapi.co/api/planets/?page=2&search=a";
        String requestURL = ReflectionTestUtils.invokeMethod(service, "putRequestURL", swapiUrl);

        String expect = swapiUrl.replaceAll("https://swapi.co/api/planets/", url);
        assertThat(expect, equalTo(requestURL));
    }
}