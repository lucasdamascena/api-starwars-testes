package com.apollo.sw.apistarwars.commons;

import com.apollo.sw.apistarwars.domains.Planet;

public class PlanetConstants {

    private PlanetConstants() {
    }

    public static final Planet PLANET = new Planet("name", "climate", "terrain");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
}
