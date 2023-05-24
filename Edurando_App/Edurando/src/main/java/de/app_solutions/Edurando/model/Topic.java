package de.app_solutions.Edurando.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private List<UserProfile> userProfiles;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Subject subject;


}
