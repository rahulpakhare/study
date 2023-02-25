package org.sapient.tbs.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "seat_booking")
public class SeatBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_booking_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @Column
    private Date bookingTime;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;
}
