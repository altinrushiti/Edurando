package de.app_solutions.Edurando.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<UserProfile> userProfiles;

    @OneToMany
    private List<Topic> topics;

    public Subject(String name) {
        this.name = name;
    }
}
