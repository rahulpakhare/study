package org.sapient.tbs.util;

import org.apache.kafka.common.protocol.types.Field;
import org.aspectj.lang.annotation.After;
import org.sapient.tbs.repository.model.Discount;
import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.repository.model.Theater;
import org.sapient.tbs.service.Impl.HandleSeatLockRequestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.sapient.tbs.util.DiscountCriteria.AFTERNOON_TIME;
import static org.sapient.tbs.util.DiscountCriteria.TICKETS;

public class DiscountCalculation {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCalculation.class);

    public static double calculateDiscount(Theater theater, SeatBooking seatBooking) {
        Double calculatedPrice = 0.0;
        List<Discount> discountList = theater.getDiscount();
        logger.info("discountList" + discountList);
        logger.info("discountList" + discountList.size());
        if (discountList.isEmpty()) {
            calculatedPrice = seatBooking.getSeats().size() * seatBooking.getPrice();
        } else {
            for (Discount discount : discountList) {
                DiscountCriteria criteria = DiscountCriteria.valueOf(discount.getCriteria());
                switch (criteria) {
                    case TICKETS:
                        calculatedPrice = calcTicketsDiscount(seatBooking, discount);
                        break;
                    case AFTERNOON_TIME:
                        calculatedPrice =  calculateAfternoonTicketDiscount(seatBooking, discount);
                        break;
                }
            }
        }

        return calculatedPrice;
    }

    private static Double calculateAfternoonTicketDiscount(SeatBooking seatBooking, Discount discount) {
        String criteria[] = AFTERNOON_TIME.getData().split("-");
        Date date = seatBooking.getStartTime();
        logger.info("startdate " + date );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        Double totalValue = seatBooking.getPrice() * (seatBooking.getSeats().size());
        if(Integer.parseInt(criteria[0]) <= hours && Integer.parseInt(criteria[1]) >= hours) {
            logger.info("AFTERNOON_TIME discount applied. date " + date);
            return  totalValue - (totalValue * ((double)discount.getPercentage() / 100));
        }

        return totalValue;
    }

    public static double calcTicketsDiscount(SeatBooking seatBooking, Discount discount) {
        Integer seatCriteria = Integer.parseInt(TICKETS.getData());
        if (seatCriteria <= seatBooking.getSeats().size()) {
            logger.info("Number of ticket discount applied. Tickets " + seatBooking.getSeats().size());
            return  seatBooking.getPrice() * (seatBooking.getSeats().size() - 1)
                    + seatBooking.getPrice() * ((double)discount.getPercentage() / 100);
        }

        return seatBooking.getPrice() * seatBooking.getSeats().size();
    }

}
