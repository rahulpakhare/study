package org.sapient.tbs.service;

import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;

import java.util.Set;

public interface CityService {
    Set<Theater> getTheaters(Long cityId);

    City createCity(City city);

    City getCity(Long cityId);

    void getMoviesInTheCity(Long cityId);
}
