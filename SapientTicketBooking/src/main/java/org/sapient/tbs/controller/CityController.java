package org.sapient.tbs.controller;


import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/city/{cityId}/theater")
    public Set<Theater>  getTheaters(@PathVariable Long cityId) {
        return cityService.getTheaters(cityId);
    }

    @PostMapping("/city")
    public City newCity(@RequestBody City city) {
       return cityService.createCity(city);
    }

    @GetMapping("/city/{cityId}")
    public City getCity(@PathVariable Long cityId) {
        return cityService.getCity(cityId);
    }
}
