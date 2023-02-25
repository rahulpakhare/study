package org.sapient.tbs.controller;

import org.sapient.tbs.repository.model.Movie;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/city/{cityId}")
    public List<Movie> getMoviesForCity(@PathVariable Long cityId) {
       return movieService.moviesForCity(cityId);
    }
}
