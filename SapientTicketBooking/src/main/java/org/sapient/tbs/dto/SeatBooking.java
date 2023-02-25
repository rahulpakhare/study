package org.sapient.tbs.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class SeatBooking implements Serializable {
    private String userName;
    private Long movieId;
    private Date startTime;
    private Long theaterId;
    private Set<Integer> seats;
    private String hashedKey;
    private Double price;
}
