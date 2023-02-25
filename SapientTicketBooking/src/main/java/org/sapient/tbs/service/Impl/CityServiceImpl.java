package org.sapient.tbs.service.Impl;

import org.sapient.tbs.exception.EntityNotFoundException;
import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.repository.CityRepository;
import org.sapient.tbs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    public Set<Theater> getTheaters(Long cityId) {
        try {
            City city = cityRepository.getReferenceById(cityId);
            return city.getTheaters();
        } catch (Exception e) {
            throw new EntityNotFoundException("City Not available");
        }
    }


    @Override
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCity(Long cityId) {
        return cityRepository.getReferenceById(cityId);
    }

    @Override
    public void getMoviesInTheCity(Long cityId) {

    }
}
