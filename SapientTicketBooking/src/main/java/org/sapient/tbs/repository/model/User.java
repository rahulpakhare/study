package org.sapient.tbs.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long Id;

    @Column
    private String name;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String emailId;

    @Column
    private String mobile;

}
