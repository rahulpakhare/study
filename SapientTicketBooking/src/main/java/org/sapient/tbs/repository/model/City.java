package org.sapient.tbs.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long Id;

    @Column (name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Theater> theaters;
}
