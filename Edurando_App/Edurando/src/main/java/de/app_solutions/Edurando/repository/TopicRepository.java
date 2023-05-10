package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {


    Optional<Topic> findById(Long id);
    Optional<Topic> findByName(String name);

    @Query("SELECT userProfiles from Topic where Topic.name = ?1")
    Optional<List<UserProfile>>  findUserProfilesByName(String  name);

}
