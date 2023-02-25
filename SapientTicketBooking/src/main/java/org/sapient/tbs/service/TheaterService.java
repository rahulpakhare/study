package org.sapient.tbs.service;

import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;

import java.util.List;
import java.util.Map;

public interface TheaterService {

    City createTheater(Theater city);

    List<org.sapient.tbs.dto.Theater> getTheatersForMovie(Map<String, String> allParams);
}
