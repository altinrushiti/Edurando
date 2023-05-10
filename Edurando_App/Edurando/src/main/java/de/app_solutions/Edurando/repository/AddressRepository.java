package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findAddressByStreetAndHouseNumberAndCityAndPostCodeAndState(String street, String houseNumber, String city, Integer postCode, String state);

}
