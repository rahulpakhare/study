package org.sapient.tbs.service.Impl;

import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.exception.SeatNotAvailableException;
import org.sapient.tbs.repository.ScreeningRepository;
import org.sapient.tbs.repository.model.Screening;
import org.sapient.tbs.service.BookingService;
import org.sapient.tbs.util.TicketBookingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import sun.security.krb5.internal.Ticket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Value(value = "${seat.lock.kafka.topic}")
    private String seatLockTopic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public boolean bookSeats(SeatBooking seatBooking) {
        Screening screening = screeningRepository.findByMovieAndTheater(seatBooking.getMovieId()
                , seatBooking.getTheaterId());
        Set<Integer> availableSeats = null;
                if (screening.getBookedSeats() != null && screening.getBookedSeats().split(",").length != 0)
                    availableSeats = TicketBookingUtil.getIntegerSetFromString(screening.getBookedSeats());
                else
                    availableSeats = new HashSet<>();

        if(TicketBookingUtil.contains(availableSeats, seatBooking.getSeats())) {
            kafkaTemplate.send(seatLockTopic, seatBooking);
        } else {
            throw new SeatNotAvailableException();
        }

        try {Thread.sleep(1000);} catch (InterruptedException ie){ie.printStackTrace();}
        return true;
    }
}
