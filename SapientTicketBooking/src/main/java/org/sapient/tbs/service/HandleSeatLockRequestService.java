package org.sapient.tbs.service;

import org.sapient.tbs.dto.SeatBooking;

public interface HandleSeatLockRequestService {
    boolean isSeatsAvailable(SeatBooking seatBooking);
    void createAsyncRequestToReserveSeats(SeatBooking seatBooking);

}
