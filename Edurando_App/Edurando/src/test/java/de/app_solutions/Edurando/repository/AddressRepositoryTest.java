package de.app_solutions.Edurando.repository;


import de.app_solutions.Edurando.model.Address;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void findAddressByStAndHouseNumberAndCityAndPostCodeAndStateTest(){

        String street = "Musterstraße";
        String houseNumber = "123";
        String city = "Musterstadt";
        Integer postCode = 12345;
        String state = "Musterland";
        Address address = new Address(street, houseNumber, city, postCode, state, null);
        addressRepository.save(address);


        Optional<Address> foundAddress = addressRepository.findAddressByStreetAndHouseNumberAndCityAndPostCodeAndState(street, houseNumber, city, postCode, state);


        assertTrue(foundAddress.isPresent());
        assertEquals(address.getId(), foundAddress.get().getId());
        assertEquals(street, foundAddress.get().getStreet());
        assertEquals(houseNumber, foundAddress.get().getHouseNumber());
        assertEquals(city, foundAddress.get().getCity());
        assertEquals(postCode, foundAddress.get().getPostCode());
        assertEquals(state, foundAddress.get().getState());
    }

}
