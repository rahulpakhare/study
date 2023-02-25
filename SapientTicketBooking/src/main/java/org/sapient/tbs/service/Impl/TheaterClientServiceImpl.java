package org.sapient.tbs.service.Impl;

import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.service.TheaterClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TheaterClientServiceImpl implements TheaterClientService {
    private static final Logger logger = LoggerFactory.getLogger(TheaterClientServiceImpl.class);

    public boolean updateClientToLockSeat() {
        logger.info("Client Locked the seat");
        return true;
    }

    @Override
    public boolean updateTheaterClientWithBookingInfo(SeatBooking seatBooking) {
        logger.info("Theater Client release locked seats");
        logger.info("Theater Client updated inventory of the seats");

        return true;
    }
}
