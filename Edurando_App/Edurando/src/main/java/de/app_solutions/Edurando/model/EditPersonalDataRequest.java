package de.app_solutions.Edurando.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditPersonalDataRequest {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String role;
    private final String personalBiography;
    private final String mobile;
    private final String profilePictureReference;
    private final String street;
    private final String houseNumber;
    private final String city;
    private final String state;
    private final String postCode;
}
