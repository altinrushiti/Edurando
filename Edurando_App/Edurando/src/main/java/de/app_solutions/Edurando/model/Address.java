package de.app_solutions.Edurando.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String houseNumber;
    private String city;
    private Integer postCode;
    private String state;

    public Address(String street, String houseNumber, String city, Integer postCode, String state, List<UserProfile> userProfile) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postCode = postCode;
        this.state = state;
        this.userProfile = userProfile;
    }

    @OneToMany
    private List<UserProfile> userProfile;
}
