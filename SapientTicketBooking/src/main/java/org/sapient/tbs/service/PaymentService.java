package org.sapient.tbs.service;

import org.sapient.tbs.dto.SeatBooking;

public interface PaymentService {
    public boolean pay(SeatBooking seatBooking);
}
