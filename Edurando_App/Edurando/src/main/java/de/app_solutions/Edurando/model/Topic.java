package de.app_solutions.Edurando.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<UserProfile> userProfile;

    @ManyToOne
    private Subject subject;

    public Topic(String name) {
        this.name = name;
    }
}
