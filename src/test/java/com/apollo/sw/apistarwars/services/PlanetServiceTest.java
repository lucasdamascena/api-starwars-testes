package com.apollo.sw.apistarwars.services;

import com.apollo.sw.apistarwars.domains.Planet;
import com.apollo.sw.apistarwars.domains.utils.QueryBuilder;
import com.apollo.sw.apistarwars.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.apollo.sw.apistarwars.commons.PlanetConstants.INVALID_PLANET;
import static com.apollo.sw.apistarwars.commons.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Test
    void createPlanet_WithValidDate_ReturnsPlanet() {
        when(planetRepository.save(PLANET)).thenReturn(PLANET);

        Planet sutPlanet = planetService.create(PLANET);

        assertThat(sutPlanet).isEqualTo(PLANET);
    }

    @Test
    void createPlanet_WithInvalidDate_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getPlanet_ByExistingId_ReturnsPlanet() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));

        Optional<Planet> sutPlanet = planetService.get(1L);

        assertThat(sutPlanet).isNotEmpty();
        assertEquals(PLANET, sutPlanet.get());
    }

    @Test
    void getPlanet_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Planet> sutPlanet = planetService.get(1L);

        assertThat(sutPlanet).isEmpty();
    }

    @Test
    void getPlanet_ByExistingName_ReturnsPlanet() {
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        Optional<Planet> sutPlanet = planetService.getByName(PLANET.getName());

        assertThat(sutPlanet).isNotEmpty();
        assertEquals(PLANET, sutPlanet.get());
    }

    @Test
    void getPlanet_ByUnexistingName_ReturnsEmpty() {
        final String name = "Unexisting Name";
        when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Planet> sutPlanet = planetService.getByName(name);

        assertTrue(sutPlanet.isEmpty());
    }

    @Test
    void listPlanets_ReturnsAllPlanets() {
        List<Planet> planets = new ArrayList<>() {
            {
                add(PLANET);
            }
        };

        Example<Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), PLANET.getTerrain()));

        when(planetRepository.findAll(query)).thenReturn(planets);

        List<Planet> sutPlanets = planetService.list(PLANET.getClimate(), PLANET.getTerrain());

        assertThat(sutPlanets).isNotEmpty().hasSize(1);
        assertThat(sutPlanets.get(0)).isEqualTo(PLANET);
    }

    @Test
    void listPlanets_ReturnsNoPlanets() {
        when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());

        List<Planet> sutPlanets = planetService.list(PLANET.getClimate(), PLANET.getTerrain());

        assertThat(sutPlanets).isEmpty();
    }

    @Test
    void removePlanet_WithExistingId_DoesNotThrowAnyException() {
        assertThatCode(() -> planetService.remove(1L)).doesNotThrowAnyException();
    }

    @Test
    void removePlanet_WithUnexistingId_ThrowsException() {
        doThrow(new RuntimeException()).when(planetRepository).deleteById(99L);

        assertThatThrownBy(() -> planetService.remove(99L)).isInstanceOf(RuntimeException.class);
    }
}