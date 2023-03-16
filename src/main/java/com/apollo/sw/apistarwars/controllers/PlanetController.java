package com.apollo.sw.apistarwars.controllers;

import com.apollo.sw.apistarwars.domains.Planet;
import com.apollo.sw.apistarwars.dto.PlanetRequest;
import com.apollo.sw.apistarwars.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody PlanetRequest planetRequest) {
        Planet planetCreated = planetService.create(planetRequest.toPlanet());
        return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);
    }
}
