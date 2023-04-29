package de.app_solutions.Edurando.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //test
    private boolean test;

    //test2 -> DEV

    //test3

    private String name;

    @ManyToMany
    private List<UserProfile> userProfile;

    @ManyToOne
    private Subject subject;
}
