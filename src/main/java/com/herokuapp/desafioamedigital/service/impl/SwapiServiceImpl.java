package com.herokuapp.desafioamedigital.service.impl;

import com.herokuapp.desafioamedigital.feign.SwapiClient;
import com.herokuapp.desafioamedigital.model.dto.v1.ListSwapiDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;
import com.herokuapp.desafioamedigital.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SwapiServiceImpl implements SwapiService {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

    private final SwapiClient swapiClient;

    private final HttpServletRequest request;

    @Autowired
    public SwapiServiceImpl(SwapiClient swapiClient, HttpServletRequest request) {
        this.swapiClient = swapiClient;
        this.request = request;
    }

    @Override
    public ListSwapiDTO findAllPlanets(String search) {
        return swapiClient.findAllPlanets(null, search, USER_AGENT);
    }

    @Override
    public Optional<PlanetDTO> findByName(String name) {
        return this.findAllPlanets(name).getResults().stream()
                .filter(p -> Objects.equals(name, p.getName()))
                .findFirst();
    }

    @Override
    public ListSwapiDTO findAllSwapiPlanets(Integer page, String search) {
        ListSwapiDTO listSwapiDTO = swapiClient.findAllPlanets(page, search, USER_AGENT);
        listSwapiDTO.setNext(putRequestURL(listSwapiDTO.getNext()));
        listSwapiDTO.setPrevious(putRequestURL(listSwapiDTO.getPrevious()));
        return listSwapiDTO;
    }

    private String putRequestURL(String swapiUrl) {
        if (swapiUrl != null) {
            StringBuffer url = request.getRequestURL();
            Matcher matcher = Pattern.compile("(page|search)=([\\d\\w]+)").matcher(swapiUrl);
            int params = 0;
            while (matcher.find()) {
                String param = matcher.group(1);
                String page = matcher.group(2);
                String pairs = params++ == 0 ? "?" : "&";
                url.append(pairs).append(param).append("=").append(page);
            }
            swapiUrl = url.toString();
        }
        return swapiUrl;
    }
}