package org.sapient.tbs.service;

import org.sapient.tbs.dto.SeatBooking;

import javax.xml.ws.Response;

public interface BookingService {
    boolean bookSeats(SeatBooking seatBooking);
}
