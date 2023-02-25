package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.repository.model.Discount;
import org.sapient.tbs.util.DiscountCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.theater.id = :theaterId")
    public Discount findByTheaterAndCity(Long theaterId);
}
