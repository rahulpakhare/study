package org.sapient.tbs.service;

import org.sapient.tbs.repository.model.Movie;
import org.sapient.tbs.repository.model.Theater;

import java.util.Date;
import java.util.List;

public interface MovieService {

    public List<Movie> moviesForCity(Long city);

}
