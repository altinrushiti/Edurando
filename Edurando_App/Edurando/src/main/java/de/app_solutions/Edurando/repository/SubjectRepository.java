package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {


    Optional<Subject> findByName(String name);

    @Query("SELECT userProfiles from Subject where Subject.name = ?1")
    Optional<List<UserProfile>>  findUserProfilesByName(String  name);

    @Query("SELECT topics from Subject where Subject.name = ?1")
    Optional<List<Topic>>  findTopicsByName(String  name);

    @Override
    List<Subject> findAll();

}
