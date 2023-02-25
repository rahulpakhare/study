package org.sapient.tbs.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theater_id")
    private Long Id;

    @Column
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    public Set<Screening> screening;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonManagedReference
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    public List<Discount> discount;
}
