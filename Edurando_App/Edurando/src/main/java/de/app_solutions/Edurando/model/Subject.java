package de.app_solutions.Edurando.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<UserProfile> userProfiles;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Topic> topics;


    public Subject(String math, List<UserProfile> userProfiles, List<Topic> topics) {
    }
}
