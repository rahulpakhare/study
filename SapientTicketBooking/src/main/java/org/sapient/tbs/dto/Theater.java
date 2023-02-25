package org.sapient.tbs.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class Theater implements Serializable {
    private String cityId;
    private String theaterId;
    private String name;
    private String movieName;
    private Date startTime;
    private Date endTime;

    private String discount;

    private Integer price;

    public Theater(BigInteger cityId, BigInteger id, String name, String movieName, Date startTime, Date endTime,
                   Integer price, String discount) {
        this.cityId = cityId.toString();
        this.theaterId = id.toString();
        this.name = name;
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.discount = discount;
    }
}
