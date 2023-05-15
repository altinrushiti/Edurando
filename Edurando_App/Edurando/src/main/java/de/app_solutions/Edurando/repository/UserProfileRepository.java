package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Address;
import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>  {
    Optional<UserProfile> findUserProfileByUsername(String email);

    @Query("SELECT userProfile from UserProfile userProfile where userProfile.id = ?1")
    Optional<UserProfile> findUserProfileById(Long id);


    //UserProfile findById(long aLong);
    @Transactional
    @Modifying
    @Query("UPDATE UserProfile a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableAppUser(String email);


    /*
        @Query("SELECT u.subjects FROM UserProfile u WHERE u.id = ?1")
        Optional<List<Subject>> findSubjectsByUserProfileId( Long id);
    */
/*    @Query(value = "SELECT DISTINCT s FROM subject s JOIN subject_user_profiles sup ON s.id = sup.subject_id JOIN user_profile up ON sup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
    Optional<List<Subject>> findSubjectsByUserProfileId(Long id);*/
/*    @Query(value = "SELECT DISTINCT s.* FROM subject s JOIN subject_user_profiles sup ON s.id = sup.subject_id JOIN user_profile up ON sup.user_profiles_id = up.id WHERE up.id = ?1", nativeQuery = true)
    String findSubjectsByUserProfileIdString(Long id);*/


}
