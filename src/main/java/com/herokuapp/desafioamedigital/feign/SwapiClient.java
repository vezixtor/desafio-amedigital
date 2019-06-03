package com.herokuapp.desafioamedigital.feign;

import com.herokuapp.desafioamedigital.model.dto.v1.ListSwapiDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "swapi", url = "https://swapi.co/api")
public interface SwapiClient {

    @GetMapping(value = "/planets/", consumes = "application/json")
    ListSwapiDTO findAllPlanets(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "search", required = false) String search,
                                @RequestHeader("user-agent") String userAgent);

    @GetMapping(value = "/planets/{id}/", consumes = "application/json")
    PlanetDTO findPlanetById(@PathVariable("id") Integer id, @RequestHeader("user-agent") String userAgent);
}
