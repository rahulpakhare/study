package org.sapient.tbs.dto;

import lombok.Getter;
import lombok.Setter;
import org.sapient.tbs.util.BookingStatus;

@Getter
@Setter
public class SeatBookingFutureResponse {
    private BookingStatus bookingStatus;
    private SeatBooking seatBooking;

    public SeatBookingFutureResponse(SeatBooking seatBooking) {
        this.seatBooking = seatBooking;
    }
}
