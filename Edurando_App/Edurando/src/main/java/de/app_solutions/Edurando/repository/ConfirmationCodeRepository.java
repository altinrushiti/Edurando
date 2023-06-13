package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.ConfirmationCode;
import de.app_solutions.Edurando.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode,Long> {
    Optional<ConfirmationCode> findByUser_Username(String email);
    //Optional<ConfirmationCode> findByEmail(String email);
    void deleteByUser_Username(String email);

}

