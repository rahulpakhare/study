package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

   // @Query("SELECT t FROM Theater t join t.screening s on t.id = s.theater WHERE t.city.id = :cityId AND s.movie.id=:movieId"), s.start_time as startTime, s.end_time as endTime
   @Query(value = "select a.*, d.criteria, d.percentage from (" +
           "select t.city_id, t.theater_id as id, t.name as theaterName, m.name as movieName, s.start_time as startTime, s.end_time as endTime, s.price from theater t join screening s on t.theater_id = s.theater_id join Movie m " +
           " on s.movie_id = m.movie_id where t.city_id = :cityId and s.movie_id = :movieId and s.start_time >= :date" +
           ") a left join Discount d ON a.id = d.theater_id", nativeQuery = true)
    List<Tuple> getTheatersForMovie(@Param("movieId") Long movieId, @Param("cityId") Long cityId, @Param("date") Date date);
}
