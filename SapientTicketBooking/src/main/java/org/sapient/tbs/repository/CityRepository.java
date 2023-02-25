package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findByName(String name);
}
