package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.Movie;
import org.sapient.tbs.repository.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

   // @Query("SELECT m from Movie m WHERE m.id in (SELECT s.movie FROM Screening s WHERE s.theater.city.id = :cityId)")
   @Query("SELECT DISTINCT s.movie FROM Screening s WHERE s.theater.city.id = :cityId")
    public List<Movie> findMoviesForCity(@Param("cityId") Long cityID);

}
