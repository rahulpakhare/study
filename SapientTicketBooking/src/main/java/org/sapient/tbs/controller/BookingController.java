package org.sapient.tbs.controller;

import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.concurrent.ForkJoinPool;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> seatBooking(@RequestBody SeatBooking seatBooking) {
         bookingService.bookSeats(seatBooking);
        return ResponseEntity.status(HttpStatus.OK).body("Seat booking done!");
    }

}
