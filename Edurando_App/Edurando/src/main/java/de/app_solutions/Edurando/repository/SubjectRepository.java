package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {


    Optional<Subject> findByName(String name);

/*    @Query("SELECT userProfiles from Subject where Subject.name = ?1")
    Optional<List<UserProfile>>  findUserProfilesByName(String  name);
*/

/*    @Query("SELECT userProfiles from Subject subject where subject.name = ?1")
    Optional<List<UserProfile>>  findUserProfilesByName(String  name);
    @Query("SELECT topics from Subject subject where subject.name = ?1")
    Optional<List<Topic>>  findTopicsByName(String  name);*/

    /*    @Query(value = "SELECT DISTINCT s FROM subject s JOIN subject_user_profiles sup ON s.id = sup.subject_id
    JOIN user_profile up ON sup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
        Optional<List<Subject>> findSubjectsByUserProfileId(Long id);*/
    List<Subject> findAllByName(String subject);

    /* @Query("SELECT subject from Subject subject where subject.name = ?1")
    Optional<Subject> findSubjectsByName(String  name);

    @Query("SELECT subject.name from Subject subject where subject.name = ?1")
    String findSubjectsByNameString(String  name);
*/
}
