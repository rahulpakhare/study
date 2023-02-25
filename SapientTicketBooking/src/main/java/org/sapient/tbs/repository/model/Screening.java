package org.sapient.tbs.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "screening_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column
    private Date date;

    @Column
    private Boolean isHouseFull;

    @Column
    private String seats;

    @Column
    private String bookedSeats;

    @Column
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getHouseFull() {
        return isHouseFull;
    }

    public void setHouseFull(Boolean houseFull) {
        isHouseFull = houseFull;
    }
}
