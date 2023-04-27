package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findUserProfileByEmail(String email);
}
