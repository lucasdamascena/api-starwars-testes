package com.apollo.sw.apistarwars.repositories;

import com.apollo.sw.apistarwars.domains.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {
}
