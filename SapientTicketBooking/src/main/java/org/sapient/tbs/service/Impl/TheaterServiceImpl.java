package org.sapient.tbs.service.Impl;

import org.sapient.tbs.repository.TheaterRepository;
import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.TheaterService;
import org.sapient.tbs.util.DiscountCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TheaterServiceImpl implements TheaterService {
    private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd-yyyy");
    @Autowired
    TheaterRepository theaterRepository;


    @Override
    public City createTheater(Theater city) {
        return null;
    }

    @Override
    public List<org.sapient.tbs.dto.Theater> getTheatersForMovie(Map<String, String> allParams) {
        List<org.sapient.tbs.dto.Theater> theaters = new ArrayList<>();
        try {
            Long movieId = Long.valueOf(allParams.get("movieId"));
            Long cityId = Long.valueOf(allParams.get("cityId"));
            Date date = simpleDateFormat.parse(allParams.get("date"));
            List<Tuple> tuples =  theaterRepository.getTheatersForMovie(movieId, cityId, date);
           theaters = tuples.stream().map(t -> new org.sapient.tbs.dto.Theater(
                    t.get(0, BigInteger.class),
                    t.get(1, BigInteger.class),
                    t.get(2, String.class),
                    t.get(3, String.class),
                    t.get(4, Date.class),
                    t.get(5, Date.class),
                    t.get(6, Integer.class),
                    getDiscountString(t.get(7, String.class), t.get(8, Integer.class))
            )).collect(Collectors.toList());
        } catch (ParseException e){
            logger.error(e.getMessage(), e);
        }

        return theaters;
    }

    private String getDiscountString(String criteria, Integer percentage) {
        if (criteria == null)
            return "NO_DISCOUNT";

        return String.format("%s-%d%%", criteria, percentage);
    }
}
