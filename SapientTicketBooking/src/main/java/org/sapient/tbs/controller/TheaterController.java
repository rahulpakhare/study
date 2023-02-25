package org.sapient.tbs.controller;

import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.TheaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/theater")
    public City createTheater(@RequestBody Theater city) {
        return theaterService.createTheater(city);
    }

    @GetMapping("/movies")
    public List<org.sapient.tbs.dto.Theater> getTheatersForMovie(@RequestParam Map<String,String> allParams) {
        List<org.sapient.tbs.dto.Theater> theaterList = theaterService.getTheatersForMovie(allParams);
        return theaterList;
    }
}
