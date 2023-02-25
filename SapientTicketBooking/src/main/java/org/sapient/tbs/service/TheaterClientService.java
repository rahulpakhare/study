package org.sapient.tbs.service;

import org.sapient.tbs.dto.SeatBooking;

public interface TheaterClientService {
    public boolean updateClientToLockSeat();
    boolean updateTheaterClientWithBookingInfo(SeatBooking seatBooking);
}
