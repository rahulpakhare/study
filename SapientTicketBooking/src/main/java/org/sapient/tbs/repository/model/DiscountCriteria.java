package org.sapient.tbs.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "discount_criteria")
@Deprecated
public class DiscountCriteria implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_criteria_id")
    private Long Id;

    @Column
    private String criteria;

    @Column
    private Double percentage;
}
