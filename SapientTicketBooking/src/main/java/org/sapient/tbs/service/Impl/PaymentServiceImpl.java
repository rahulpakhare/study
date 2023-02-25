package org.sapient.tbs.service.Impl;

import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.repository.TheaterRepository;
import org.sapient.tbs.repository.model.Discount;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.PaymentService;
import org.sapient.tbs.util.DiscountCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private TheaterRepository theaterRepository;

    @Transactional
    @Override
    public boolean pay(SeatBooking seatBooking){
        logger.info("inside pay service");
        Double ticketPrice = 0.0;
        Theater theater = theaterRepository.findById(seatBooking.getTheaterId()).get();
        logger.info("theater " + theater);
        ticketPrice = DiscountCalculation.calculateDiscount(theater, seatBooking);
        logger.info("Payment of RS " +ticketPrice+" completed ");

        return true;
    }
}
