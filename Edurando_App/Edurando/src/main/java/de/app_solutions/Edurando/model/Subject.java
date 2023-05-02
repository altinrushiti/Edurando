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

    private boolean test;

    private String name;

    @ManyToMany
    private List<UserProfile> userProfile;

    @ManyToOne
    private Subject subject;
}
