package com.herokuapp.desafioamedigital.controller;

import com.herokuapp.desafioamedigital.model.dto.v1.ListSwapiDTO;
import com.herokuapp.desafioamedigital.model.dto.v1.PlanetaDTO;
import com.herokuapp.desafioamedigital.service.PlanetaService;
import com.herokuapp.desafioamedigital.service.SwapiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/v1/planeta")
@Api(value = "PlanetaControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Planeta")
public class PlanetaController {
    private static final String RECURSO = "Planeta";

    private final PlanetaService planetaService;

    private final SwapiService swapiService;

    @Autowired
    public PlanetaController(PlanetaService planetaService, SwapiService swapiService) {
        this.planetaService = planetaService;
        this.swapiService = swapiService;
    }

    @GetMapping
    @ApiOperation("Retorna a lista de registros com paginação")
    public Page<PlanetaDTO> findAll(Pageable pageable, @RequestParam(required = false) String nome) {
        log.debug("Listar todos {} ", RECURSO);
        return this.planetaService.findAll(pageable, nome);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna o registro do ID informado")
    public PlanetaDTO findById(@PathVariable Long id) {
        log.debug("Encontra um registro de {} ", RECURSO);
        return this.planetaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastra um novo registro")
    public PlanetaDTO save(@Validated @RequestBody PlanetaDTO dto) {
        log.debug("Cadastrar {}", RECURSO);
        return this.planetaService.save(dto);
    }

    @PutMapping("{id}")
    @ApiOperation("Realiza alterações no registro do ID informado")
    public PlanetaDTO update(@PathVariable Long id, @Validated @RequestBody PlanetaDTO dto) {
        log.debug("Atualizar {} ", RECURSO);
        return this.planetaService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Exclui um registro com base no ID indicado")
    public void delete(@PathVariable Long id) {
        log.debug("Deletando dado {} ", RECURSO);
        this.planetaService.delete(id);
    }

    @GetMapping("swapi")
    @ApiOperation("Retorna a lista de registros no Swapi")
    public ListSwapiDTO findAllSwapiPlanets(@RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false) String search) {
        log.debug("Listar todos {} da Swapi", RECURSO);
        return this.swapiService.findAllSwapiPlanets(page, search);
    }
}