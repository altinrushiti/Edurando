package de.app_solutions.Edurando.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingID;
    private Float stars;
    private String comment;

    @ManyToOne
    private UserProfile author;
}
