package org.sapient.tbs.service.Impl;

import org.sapient.tbs.repository.*;
import org.sapient.tbs.repository.model.*;
import org.sapient.tbs.service.GenerateDataService;
import org.sapient.tbs.util.DiscountCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenerateDataServiceImpl implements GenerateDataService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountRepository discountRepository;

    private Integer dataSize = 10;

    public List<Movie> getMovieData() {
        String[] movieNames = new String[]{"ABCD Dance", "HIT", "ANEK", "2.12", "KGF", "KGF 2"};
        List<Movie> movies = new ArrayList<>(dataSize);
        for(int i = 0; i<6; i++) {
            Movie movie = new Movie();
            movie.setName(movieNames[i]);
            movie.setDurationInMins(180);
           movies.add(movieRepository.saveAndFlush(movie));
        }
        return movies;
    }

    public List<Screening> getScreeningData(List<Movie> movies, List<Theater> theaters) {


        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < 50) {
            if(i != 0)
                sb.append(",");
            sb.append(++i);
        }

        List<Screening> screenings = new ArrayList<>(dataSize);
        for(Theater theater: theaters) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            calendar.set(Calendar.MINUTE, 00);

            for (i = 0; i < 6; i++) {
                Screening screeningTheater1 = new Screening();
                screeningTheater1.setDate(calendar.getTime());
                screeningTheater1.setSeats(sb.toString());
                screeningTheater1.setStartTime(calendar.getTime());
                screeningTheater1.setIsHouseFull(false);
                screeningTheater1.setPrice(350);

                calendar.add(Calendar.HOUR_OF_DAY, 3);
                screeningTheater1.setEndTime(calendar.getTime());
                screeningTheater1.setMovie(movies.get(i));
                screeningTheater1.setTheater(theater);
                screenings.add(screeningRepository.saveAndFlush(screeningTheater1));
            }
        }

        return screenings;
    }

    public List<Theater> getTheaterData(City[] cities) {
        List<Theater> theaters = new ArrayList<>(dataSize);
        int size = 5;
        int i = 0;
        for(City city: cities) {
            for (; i < size; i++) {
                Theater theaterPune1 = new Theater();
                theaterPune1.setName("Inox " + i);
                theaterPune1.setCity(city);
                theaters.add(theaterRepository.saveAndFlush(theaterPune1));
            }
            size +=5;
        }
        return theaters;
    }

    @Override
    public void generateTestData() {
        City cityPune = new City();
        cityPune.setName("Pune");
        City cityMumbai = new City();
        cityMumbai.setName("Mumbai");
        City cityNashik = new City();
        cityNashik.setName("Nashik");
        City cityNagpur = new City();
        cityNagpur.setName("Nagpur");
        City cityNagar = new City();
        cityNagar.setName("Nagar");

        cityRepository.saveAndFlush(cityPune);
        cityRepository.saveAndFlush(cityMumbai);
        cityRepository.saveAndFlush(cityNashik);
        cityRepository.saveAndFlush(cityNagpur);
        cityRepository.saveAndFlush(cityNagar);

        City[] cities = new City[]{cityPune, cityMumbai, cityNashik, cityNagpur, cityNagar};
        List<Theater> theaters = getTheaterData(cities);

        getScreeningData(getMovieData(), theaters);

        User user = new User();
        user.setName("eva");
        user.setUserName("eva");
        user.setMobile("1234567890");
        user.setPassword("eva@123");
        user.setEmailId("eva@email.com");
        userRepository.save(user);

        Discount discount = new Discount();
        discount.setCriteria(DiscountCriteria.AFTERNOON_TIME.name());
        discount.setPercentage(20);
        discount.setTheater(theaters.get(1));
        discountRepository.save(discount);

        discount = new Discount();
        discount.setCriteria(DiscountCriteria.TICKETS.name());
        discount.setPercentage(50);
        discount.setTheater(theaters.get(2));
        discountRepository.save(discount);
    }
}
