package de.app_solutions.Edurando.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<UserProfile> userProfiles;

    @ManyToOne
    private Subject subject;

    public Topic(String name) {
        this.name = name;
    }
}
