package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("Select s FROM Screening s WHERE s.movie.id = :movieId and s.theater.id= :theaterId")
    public Screening findByMovieAndTheater(Long movieId, Long theaterId);
}
