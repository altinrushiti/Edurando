package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Address;
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

    Optional<UserProfile> findUserProfileById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UserProfile a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableAppUser(String email);


    /*
        @Query("SELECT u.subjects FROM UserProfile u WHERE u.id = ?1")
        Optional<List<Subject>> findSubjectsByUserProfileId( Long id);
    */
    @Query(value = "SELECT DISTINCT s.* FROM subject s JOIN subject_user_profiles sup ON s.id = sup.subject_id JOIN user_profile up ON sup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
    List<String> findSubjectsByUserProfileId(Long id);

    /*    @Query(value = "SELECT DISTINCT s.* FROM subject s JOIN subject_user_profiles sup ON s.id = sup.subject_id JOIN user_profile up ON sup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
        String findSubjectsByUserProfileIdString(Long id);*/
    @Query(value = "SELECT DISTINCT t.* FROM topic t JOIN topic_user_profiles tup ON t.id = tup.topic_id JOIN user_profile up ON tup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
    List<String> findTopicByUserProfileId(Long id);

    List<UserProfile> findTop10ByOrderByRatingDesc();
}
