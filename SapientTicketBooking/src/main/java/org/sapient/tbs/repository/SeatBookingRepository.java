package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {
}
