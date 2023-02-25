package org.sapient.tbs.service.Impl;

import org.sapient.tbs.repository.MovieRepository;
import org.sapient.tbs.repository.model.Movie;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> moviesForCity(Long city) {
        return movieRepository.findMoviesForCity(city);
    }

}
