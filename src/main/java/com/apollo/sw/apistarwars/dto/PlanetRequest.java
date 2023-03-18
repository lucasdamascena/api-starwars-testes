package com.apollo.sw.apistarwars.dto;

import com.apollo.sw.apistarwars.domains.Planet;

import javax.validation.constraints.NotEmpty;

public class PlanetRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String climate;

    @NotEmpty
    private String terrain;

    public PlanetRequest(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public Planet toPlanet() {
        return new Planet(name, climate, terrain);
    }
}
