package de.app_solutions.Edurando.model;

import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EditPersonalDataRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    private String personalBiography;
    private String mobile;
    private String street;
    private String houseNumber;
    private String city;
    private String state;
    private String postCode;

}
