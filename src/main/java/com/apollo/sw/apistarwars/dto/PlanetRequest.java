package com.apollo.sw.apistarwars.dto;

import com.apollo.sw.apistarwars.domains.Planet;

public class PlanetRequest {

    private Long id;
    private String name;
    private String climate;
    private String terrain;

    public PlanetRequest(Long id, String name, String climate, String terrain) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Long getId() {
        return id;
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
        return new Planet(id, name, climate, terrain);
    }
}
