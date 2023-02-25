package org.sapient.tbs.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.metrics.internals.IntGaugeSuite;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_id")
    private Long Id;

    @Column
    private String criteria;

    @Column
    private Integer percentage;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
}