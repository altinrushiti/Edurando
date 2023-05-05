package de.app_solutions.Edurando.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateRequest {
    // Personal Data
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String role;
    private final String personalBiography;
    private final String mobile;
    private final String profilePictureReference;

    // Address
    private final String street;
    private final String houseNumber;
    private final String city;
    private final String state;
    private final Integer postCode;

    // Uni Stuff
    private final String Subject;
    private final String Topic;

}
