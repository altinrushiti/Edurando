package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findUserProfileByUsername(String email);




    @Transactional
    @Modifying
    @Query("UPDATE UserProfile a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableAppUser(String email);


}
