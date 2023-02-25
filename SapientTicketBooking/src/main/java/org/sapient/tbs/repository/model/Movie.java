package org.sapient.tbs.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long Id;

    @Column
    private String name;

    @Column
    private Integer durationInMins;
}
